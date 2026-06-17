//
//  ArithmeticElement.swift
//  CountOnMe
//
//  Created by damien on 18/06/2022.
//  Copyright © 2022 Vincent Saluzzo. All rights reserved.
//

import Foundation

enum ArithmeticElement: RawRepresentable, Equatable {
    
    init(rawValue: String) {
        switch rawValue {
        case "+":
            self = .addition
        case "-":
            self = .substraction
        case "/":
            self = .division
        case "*":
            self = .multiplication
        default:
            self = .none
        }
    }
    
    var rawValue: String {
        switch self {
        case .addition:
            return ("+")
        case .substraction :
            return ("-")
        case  .division :
            return ("/")
        case  .multiplication:
            return ("*")
        case let .number(double):
            return ("\(double)")
        case .evaluation:
            return ("=")
        default:
            return ("")
        }
    }
    
    typealias RawValue = String
    
    case number(Double)
    case none
    case evaluation
    case addition
    case substraction
    case division
    case multiplication
    
    func evaluate(left: ArithmeticElement = number(0.0), right: ArithmeticElement = number(0.0)) throws -> Double {
        switch self {
        case let .number(value):
            return value
        case .addition:
            return try left.evaluate() + right.evaluate()
        case .substraction:
            return try left.evaluate() - right.evaluate()
        case .multiplication:
            return try left.evaluate() * right.evaluate()
        case .division:
            if try right.evaluate() == 0 {
                throw ArithmeticError.DivisionByZero
            }
            return try left.evaluate() / right.evaluate()
        default:
            return 0.0
        }
    }
}

extension ArithmeticElement: CaseIterable {
    static var allCases: [ArithmeticElement] {
        return [.number(0.0), .addition, .substraction,
                    .multiplication, .division,
                    .none, .evaluation
            ]
        }
}

