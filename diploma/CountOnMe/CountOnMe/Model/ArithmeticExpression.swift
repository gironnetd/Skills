//
//  ArithmeticExpression.swift
//  CountOnMe
//
//  Created by damien on 14/06/2022.
//  Copyright © 2022 Vincent Saluzzo. All rights reserved.
//

import Foundation

struct ArithmeticExpression {
    
    private var elements: [ArithmeticElement] = []
    
    // Error check computed variables
    private var isCorrect: Bool {
        switch elements.last {
        case .addition, .substraction, .division, .multiplication, nil:
            return false
        default:
            return true
        }
    }
    
    private var haveEnoughElement: Bool {
        return elements.count >= 3
    }
    
    private var canAddOperator: Bool {
        switch elements.last {
        case .addition, .substraction, .division, .multiplication, nil:
            return false
        default:
            return true
        }
    }
    
    private var haveResult: Bool {
        return elements.contains(.evaluation)
    }
    
    mutating func clear() {
        elements.removeAll()
    }
    
    mutating func addNumber(number: Double) throws {
        guard !haveResult else {
            clear()
            elements.append(.number(number))
            throw ArithmeticError.ExpressionHaveResult
        }
        
        switch elements.last {
        case .number (let lastElement):
            elements.remove(at: elements.endIndex - 1)
            elements.append(.number((lastElement * 10) + number))
        default:
            elements.append(.number(number))
        }
    }
    
    mutating func addOperator(arithmeticOperator: String) throws {
        guard elements.last != nil else {
            throw ArithmeticError.ExpressionIsIncorrect
        }
        
        guard canAddOperator else {
            throw ArithmeticError.CannotAddOperator
        }
        
        let newElement = ArithmeticElement(rawValue: arithmeticOperator)
        
        guard newElement != .none else {
            throw ArithmeticError.UnknownOperator
        }
        
        elements.append(newElement)
        
        guard !haveResult else {
            if let firstIndex = elements.firstIndex(of: .evaluation) {
                elements.removeSubrange(0...firstIndex)
            }
            throw ArithmeticError.ExpressionHaveResult
        }
    }
    
    @discardableResult
    mutating func evaluate() throws -> String {
        guard !haveResult else {
            throw ArithmeticError.ExpressionHaveResult
        }
        
        guard haveEnoughElement else {
            throw ArithmeticError.ExpressionHaveNotEnoughElement
        }
        
        guard isCorrect else {
            throw ArithmeticError.ExpressionIsIncorrect
        }
        
        // Create local copy of operations
        var operations = try elements.reduce(into: elements, { result, element in
            if element == .multiplication || element == .division {
                if let priorityIndex = result.firstIndex(of: element) {
                    let double = try result[priorityIndex].evaluate(left: result[priorityIndex - 1], right: result[priorityIndex + 1])
                    result.remove(at: priorityIndex + 1)
                    result.remove(at: priorityIndex)
                    result.remove(at: priorityIndex - 1)
                    result.insert(.number(double), at: priorityIndex - 1)
                }
            }
        })
        
        let result = try operations.reduce(operations[0].evaluate(), { result, element in
            if operations.count > 1 {
                let double = try operations[1].evaluate(left: operations[0], right: operations[2])
                operations.remove(at:2)
                operations.remove(at: 1)
                operations.remove(at: 0)
                operations.insert(.number(double), at: 0)
                return double
            }
            return result
        }) 
        
        elements.removeAll()
        elements.append(.evaluation)
        elements.append(.number(result))
        
        return "\(result)"
    }
}
