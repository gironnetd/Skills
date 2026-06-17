//
//  ArithmeticError.swift
//  CountOnMe
//
//  Created by damien on 15/06/2022.
//  Copyright © 2022 Vincent Saluzzo. All rights reserved.
//

import Foundation

enum ArithmeticError: RawRepresentable, Error, Equatable {
    
    typealias RawValue = (title: String,message: String)
    
    init?(rawValue: (title: String, message: String)) {
        switch rawValue.message {
        case Constants.divisionByZeroIsImpossible:
            self = .DivisionByZero
        case Constants.expressionIsIncorrect:
            self = .ExpressionIsIncorrect
        case Constants.expressionHaveNotEnoughElement:
            self = .ExpressionHaveNotEnoughElement
        case Constants.expressionHaveResult:
            self = .ExpressionHaveResult
        case Constants.cannotAddOperator:
            self = .CannotAddOperator
        case Constants.unknownOperator:
            self = .UnknownOperator
        default:
            self = .None
        }
    }
    
    var rawValue: (title: String,message: String) {
        switch self {
        case .DivisionByZero :
            return (Constants.titleDivisionByZero, Constants.divisionByZeroIsImpossible)
        case .ExpressionIsIncorrect :
            return (Constants.titleExpressionIsIncorrect, Constants.expressionIsIncorrect)
        case .ExpressionHaveNotEnoughElement :
            return (Constants.titleExpressionHaveNotEnoughElement, Constants.expressionHaveNotEnoughElement)
        case .ExpressionHaveResult :
            return (Constants.titleExpressionHaveResult, Constants.expressionHaveResult)
        case .CannotAddOperator :
            return (Constants.titleCannotAddOperator, Constants.cannotAddOperator)
        case .UnknownOperator :
            return (Constants.titleUnknownOperator, Constants.unknownOperator)
        case .None:
            return ("", "")
        }
    }
    
    case None
    case DivisionByZero
    case ExpressionIsIncorrect
    case ExpressionHaveNotEnoughElement
    case ExpressionHaveResult
    case CannotAddOperator
    case UnknownOperator
}

extension ArithmeticError: CaseIterable {
    static var allCases: [ArithmeticError] {
            return [.None, .DivisionByZero, .ExpressionIsIncorrect,
                    .ExpressionHaveNotEnoughElement, .ExpressionHaveResult,
                    .CannotAddOperator, .UnknownOperator
            ]
        }
}

