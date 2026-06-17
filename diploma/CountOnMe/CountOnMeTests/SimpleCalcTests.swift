//
//  SimpleCalcTests.swift
//  SimpleCalcTests
//
//  Created by Vincent Saluzzo on 29/03/2019.
//  Copyright © 2019 Vincent Saluzzo. All rights reserved.
//

import XCTest
@testable import CountOnMe

class SimpleCalcTests: XCTestCase {
    
    private lazy var expression: ArithmeticExpression = ArithmeticExpression()
    
    private lazy var formatter = NumberFormatter()

    private var firstValue: Double = 60
    private var secondValue: Double = 3
    private var thirdValue: Double = 3.55
    
    override func setUp() {
        expression = ArithmeticExpression()
        formatter.maximumFractionDigits =  2
    }
    
    override func tearDown() {
        expression.clear()
    }
    
    // **************** Testing ArithmeticExpression **************** //
    
    
    // **************** Testing Addition **************** //
    
    func test_GivenTwoNumberAndMakeAddition_WhenEvaluate_ThenAdditionIsCorrect() throws {
        // Given two Number and make addition
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "+")
        try expression.addNumber(number: secondValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue + secondValue)
        
        // Then Addition Is Correct
        XCTAssert(actual == expected)
    }
    
    func test_GivenOneNumberHigherThanTenAndAnotherONeAndMakeAddition_WhenEvaluate_ThenAdditionIsCorrect() throws {
        // Given One Number Higher Than Ten And Another One
        try expression.addNumber(number:firstValue)
        try expression.addNumber(number: firstValue)
        
        // And Make Addition
        try expression.addOperator(arithmeticOperator: "+")
        
        try expression.addNumber(number: secondValue)
        try expression.addNumber(number: secondValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String((firstValue * 10 + firstValue) + (secondValue * 10 + secondValue))
        
        // Then Addition Is Correct
        XCTAssert(actual == expected)
    }
    
    // **************** Testing Substraction **************** //
    
    func test_GivenTwoNumberAndMakeSubstraction_WhenEvaluate_ThenSubstractionIsCorrect() throws {
        // Given Two Number And Make Substraction
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "-")
        try expression.addNumber(number: secondValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue - secondValue)
        
        // Then Substraction Is Correct
        XCTAssert(actual == expected)
    }
    
    func test_GivenOneNumberHigherThanTenAndAnotherAndMakeSubstraction_WhenEvaluate_ThenSubstractionIsCorrect() throws {
        // Given One Number Higher Than Ten And Another
        try expression.addNumber(number:firstValue)
        try expression.addNumber(number: firstValue)
        
        // And Make Substraction
        try expression.addOperator(arithmeticOperator: "-")
        
        try expression.addNumber(number: secondValue)
        try expression.addNumber(number: secondValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String((firstValue * 10 + firstValue) - (secondValue * 10 + secondValue))
        
        // Then Substraction Is Correct
        XCTAssert(actual == expected)
    }
    
    // **************** Testing Addition And Substraction **************** //
    
    func test_GivenThreeNumberAndMakeAdditionAndSubstraction_WhenEvaluate_ThenResultIsCorrect() throws {
        // Given Three Number And Make Addition And Substraction
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "+")
        try expression.addNumber(number: secondValue)
        try expression.addOperator(arithmeticOperator: "-")
        try expression.addNumber(number: thirdValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue + secondValue - thirdValue)
        
        // Then Result Is Correct
        XCTAssert(actual == expected)
    }
    
    // **************** Testing Multiplication **************** //
    
    func test_GivenTwoNumberAndMakeMultiplication_WhenEvaluate_ThenMultiplicationIsCorrect() throws {
        // Given Two Number And Make Multiplication
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "*")
        try expression.addNumber(number: secondValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue * secondValue)
        
        // Then Multiplication Is Correct
        XCTAssert(actual == expected)
    }
    
    func test_GivenOneNumberHigherThanTenAndAnotherAndMakeMultiplication_WhenEvaluate_ThenMultiplicationIsCorrect() throws {
        // Given One Number Higher Than Ten And Another
        try expression.addNumber(number:firstValue)
        try expression.addNumber(number: firstValue)
        
        // And Make Multiplication
        try expression.addOperator(arithmeticOperator: "*")
        
        try expression.addNumber(number: secondValue)
        try expression.addNumber(number: secondValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String((firstValue * 10 + firstValue) * (secondValue * 10 + secondValue))
        
        // Then Multiplication Is Correct
        XCTAssert(actual == expected)
    }
    
    // **************** Testing Division **************** //
    
    func test_GivenTwoNumberAndMakeDivision_WhenEvaluate_ThenDivisionIsDecimal() throws {
        // Given Two Number And Make Division
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "/")
        try expression.addNumber(number: secondValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue / secondValue)
        XCTAssert(actual == expected)
        
        // Then Division Is Decimal
        XCTAssert(Decimal(string: actual) == Decimal(string: expected))
    }
    
    func test_GivenTwoNumberAndMakeDivision_WhenEvaluate_ThenDivisionIsCorrect() throws {
        // Given Two Number And Make Division
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "/")
        try expression.addNumber(number: secondValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue / secondValue)
        
        // Then Division Is Correct
        XCTAssert(actual == expected)
    }
    
    func test_GivenOneNumberHigherThanTenAndAnotherAndMakeDivision_WhenEvaluate_ThenDivisionIsCorrect() throws {
        // Given One Number Higher Than Ten And Another
        try expression.addNumber(number:firstValue)
        try expression.addNumber(number: firstValue)
        
        // And Make Division
        try expression.addOperator(arithmeticOperator: "/")
        
        try expression.addNumber(number: secondValue)
        try expression.addNumber(number: secondValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String((firstValue * 10 + firstValue) / (secondValue * 10 + secondValue))
        
        // Then Division Is Correct
        XCTAssert(actual == expected)
    }
    
    // **************** Testing Multiplication And Division **************** //
    
    func test_GivenThreeNumberAndMakeMultiplicationAndDivision_WhenEvaluate_ThenResultIsCorrect() throws {
        // Given Three Number And Make Multiplication And Division
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "*")
        try expression.addNumber(number: secondValue)
        try expression.addOperator(arithmeticOperator: "/")
        try expression.addNumber(number: thirdValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue * secondValue / thirdValue)
        
        // Then Result Is Correct
        XCTAssert(actual == expected)
    }
    
    func test_GivenThreeNumberAndMakeDivisionAndMultiplication_WhenEvaluate_ThenResultIsCorrect() throws {
        // Given Three Number And Make Division And Multiplication
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "/")
        try expression.addNumber(number: secondValue)
        try expression.addOperator(arithmeticOperator: "*")
        try expression.addNumber(number: thirdValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue / secondValue * thirdValue)
        
        // Then Result Is Correct
        XCTAssert(actual == expected)
    }
    
    // **************** Testing Long Number Operation **************** //
    
    func test_GivenTwoLongNumberAndMakeMultiplication_WhenEvaluate_ThenMultiplicationIsCorrect() throws {
        // Given Two Long Number And Make Multiplication
        firstValue = 80000000000000
        secondValue = 80000000000000
        
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "*")
        try expression.addNumber(number: secondValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue * secondValue)
        
        // Then Multiplication Is Correct
        XCTAssert(actual == expected)
    }
    
    // **************** Testing Operator Priority **************** //
    
    func test_GivenThreeNumberAndMakeAdditionAndMultiplication_WhenEvaluate_ThenOperatorPriorityIsRespectedAndResultIsCorrect() throws {
        // Given Three Number And Make Addition And Multiplication
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "+")
        try expression.addNumber(number: secondValue)
        try expression.addOperator(arithmeticOperator: "*")
        try expression.addNumber(number: thirdValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue + secondValue * thirdValue)
        
        // Then Operator Priority Is Respected And Result Is Correct
        XCTAssert(actual == expected)
    }
    
    func test_GivenThreeNumberAndMakeMultiplicationAndAddition_WhenEvaluate_ThenOperatorPriorityIsRespectedAndResultIsCorrect() throws {
        // Given Three Number And Make Addition And Multiplication
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "*")
        try expression.addNumber(number: secondValue)
        try expression.addOperator(arithmeticOperator: "+")
        try expression.addNumber(number: thirdValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String((firstValue * secondValue) + thirdValue)
        
        // Then Operator Priority Is Respected And Result Is Correct
        XCTAssert(actual == expected)
    }
    
    func test_GivenThreeNumberAndMakeSubstractionAndMultiplicationAndAddition_WhenEvaluate_ThenOperatorPriorityIsRespectedAndResultIsCorrect() throws {
        // Given Three Number And Make Addition And Multiplication
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "-")
        try expression.addNumber(number: secondValue)
        try expression.addOperator(arithmeticOperator: "*")
        try expression.addNumber(number: secondValue)
        try expression.addOperator(arithmeticOperator: "+")
        try expression.addNumber(number: thirdValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue - (secondValue * secondValue) + thirdValue)
        
        // Then Operator Priority Is Respected And Result Is Correct
        XCTAssert(actual == expected)
    }
    
    func test_GivenThreeNumberAndMakeSubstractionAndDivisionAndAddition_WhenEvaluate_ThenOperatorPriorityIsRespectedAndResultIsCorrect() throws {
        // Given Three Number And Make Addition And Multiplication
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "-")
        try expression.addNumber(number: secondValue)
        try expression.addOperator(arithmeticOperator: "/")
        try expression.addNumber(number: secondValue)
        try expression.addOperator(arithmeticOperator: "+")
        try expression.addNumber(number: thirdValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue - (secondValue / secondValue) + thirdValue)
        
        // Then Operator Priority Is Respected And Result Is Correct
        XCTAssert(actual == expected)
    }
    
    func test_GivenThreeNumberAndMakeSubstractionAndMultiplication_WhenEvaluate_ThenOperatorPriorityIsRespectedAndResultIsCorrect() throws {
        // Given Three Number And Make Substraction And Multiplication
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "-")
        try expression.addNumber(number: secondValue)
        try expression.addOperator(arithmeticOperator: "*")
        try expression.addNumber(number: thirdValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue - secondValue * thirdValue)
        
        // Then Operator Priority Is Respected And Result Is Correct
        XCTAssert(actual == expected)
    }
    
    func test_GivenThreeNumberAndMakeSubstractionAndDivision_WhenEvaluate_ThenOperatorPriorityIsRespectedAndResultIsCorrect() throws {
        // Given Three Number And Make Substraction And Division
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "-")
        try expression.addNumber(number: secondValue)
        try expression.addOperator(arithmeticOperator: "/")
        try expression.addNumber(number: thirdValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue - secondValue / thirdValue)
        
        // Then Operator Priority Is Respected And Result Is Correct
        XCTAssert(actual == expected)
    }
    
    func test_GivenThreeNumberAndMakeAdditionAndDivision_WhenEvaluate_ThenOperatorPriorityIsRespectedAndResultIsCorrect() throws {
        // Given Three Number And Make Addition And Division
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "+")
        try expression.addNumber(number: secondValue)
        try expression.addOperator(arithmeticOperator: "/")
        try expression.addNumber(number: thirdValue)
        
        // When Evaluate
        let actual = String(try expression.evaluate())
        let expected = String(firstValue + secondValue / thirdValue)
        
        // Then Operator Priority Is Respected And Result Is Correct
        XCTAssert(actual == expected)
    }
    
    // **************** Testing ArithmeticError **************** //
    
    func test_GivenExpressionIsIncorrect_WhenEvaluate_ThenExpressionIsIncorrectErrorIsThrown() throws {
        // Given Expression Is Incorrect
        try expression.addNumber(number: firstValue)
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "+")
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "+")
        
        do {
            // When Evaluate
            try expression.evaluate()
        } catch let error as ArithmeticError {
            switch error {
            case .ExpressionIsIncorrect:
                
                // Then ExpressionIsIncorrect Error Is Thrown
                XCTAssert(true)
            default:
                XCTAssert(false)
            }
        }
    }
    
    func test_GivenExpressionHaveNotEnoughElement_WhenEvaluate_ThenExpressionHaveNotEnoughElementErrorIsThrown() throws {
        // Given Expression Have Not Enough Element
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "+")
        
        do {
            // When Evaluate
            try expression.evaluate()
        } catch let error as ArithmeticError {
            switch error {
            case .ExpressionHaveNotEnoughElement:
                
                // Then ExpressionHaveNotEnoughElement Error Is Thrown
                XCTAssert(true)
            default:
                XCTAssert(false)
            }
        }
    }

    func test_GivenAddAOperator_WhenAddAOperatorAgain_ThenCannotAddOperatorErrorIsThrown() throws {
        do {
            try expression.addNumber(number: firstValue)
            // Given Add A Operator
            try expression.addOperator(arithmeticOperator: "+")
            // When Add A Operator Again
            try expression.addOperator(arithmeticOperator: "*")
        } catch let error as ArithmeticError {
            switch error {
            case .CannotAddOperator:
                
                // Then CannotAddOperator Error Is Thrown
                XCTAssert(true)
            default:
                XCTAssert(false)
                break
            }
        }
    }
    
    func test_GivenTwoNumber_WhenAddUnknownOperatorAndEvaluate_ThenUnknownOperatorErrorIsThrown() throws {
        // Given Two Number
        do {
            try expression.addNumber(number: firstValue)
            // When Add Unknown Operator And Evaluate
            try expression.addOperator(arithmeticOperator: "+")
            try expression.addNumber(number: secondValue)
            try expression.addOperator(arithmeticOperator: "/+")
            try expression.addNumber(number: thirdValue)
            try expression.evaluate()
        } catch let error as ArithmeticError {
            switch error {
            case .UnknownOperator:
                
                // Then Unknown Operator Error Is Thrown
                XCTAssert(true)
            default:
                XCTAssert(false)
            }
        }
    }
    
    func test_GivenTwoNumberAndMakeDivisionAndDivideByZero_WhenEvaluate_ThenDivisionByZeroErrorIsThrown() throws {
        // Given TwoNumber And Make Division And Divide By Zero
        do {
            try expression.addNumber(number: firstValue)
            try expression.addOperator(arithmeticOperator: "/")
            try expression.addNumber(number: 0)
            // When Evaluate
            try expression.evaluate()
        } catch let error as ArithmeticError {
            switch error {
            case .DivisionByZero:
                
                // Then Division By Zero Error Is Thrown
                XCTAssert(true)
            default:
                XCTAssert(false)
            }
        }
    }
    
    func test_GivenNoNumberAdded_WhenAddOperator_ThenExpressionIsIncorrectErrorIsThrown() throws {
        // Given No Number Added
        do {
            // WhenAddOperator
            try expression.addOperator(arithmeticOperator: "/")
            try expression.addNumber(number: 0)
            
            try expression.evaluate()
        } catch let error as ArithmeticError {
            switch error {
            case .ExpressionIsIncorrect:
                
                // Then Expression Is Incorrect Error Is Thrown
                XCTAssert(true)
            default:
                XCTAssert(false)
            }
        }
    }
    
    func test_GivenTwoNumberAndMakeAdditionAndEvaluate_WhenEvaluateAgain_ThenExpressionHaveResultErrorIsThrown() throws {
        // Given Two Number And Make Addition
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "+")
        try expression.addNumber(number: secondValue)
        
        // And Evaluate
        try expression.evaluate()
        
        do {
            // When Evaluate Again
            try expression.evaluate()
        } catch let error as ArithmeticError {
            switch error {
            case .ExpressionHaveResult:
                
                // Then Expression Has Been Cleared And Replace By Added Number
                XCTAssert(true)
            default:
                XCTAssert(false)
            }
        }
    }
    
    func test_GivenOneNumberAndOneOperator_WhenEvaluate_ThenExpressionHaveNotEnoughElementErrorIsThrown() throws {
        // Given One Number And One Operator
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "+")
        
        do {
            // When Evaluate
            try expression.evaluate()
        } catch let error as ArithmeticError {
            switch error {
            case .ExpressionHaveNotEnoughElement:
                
                // Then Expression Have Not Enough Element Error Is Thrown
                XCTAssert(true)
            default:
                XCTAssert(false)
            }
        }
    }
    
    func test_GivenTwoNumberAndMakeAdditionAndEvaluate_WhenAddNumber_ThenExpressionHaveResultErrorIsThrown() throws {
        // Given Two Number And Make Addition
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "+")
        try expression.addNumber(number: secondValue)
        
        // And Evaluate
        try expression.evaluate()
        
        do {
            // When Add Number
            try expression.addNumber(number: firstValue)
        } catch let error as ArithmeticError {
            
            switch error {
            case .ExpressionHaveResult:
                
                // Then Expression Have Result Error Is Thrown
                XCTAssert(true)
            default:
                XCTAssert(false)
            }
        }
    }
    
    func test_GivenTwoNumberAndMakeAdditionAndEvaluate_WhenAddOperator_ThenOperatorHasBeenAddedToResult() throws {
        // Given Two Number And Make Addition
        try expression.addNumber(number: firstValue)
        try expression.addOperator(arithmeticOperator: "+")
        try expression.addNumber(number: secondValue)
        
        // And Evaluate
        try expression.evaluate()
        
        do {
            // When Add Operator
            try expression.addOperator(arithmeticOperator: "*")
        } catch let error as ArithmeticError {
            switch error {
            case .ExpressionHaveResult:
                
                // Then Expression Has Been Cleared And Replace By Added Number
                XCTAssert(true)
            default:
                XCTAssert(false)
            }
        }
        
        try expression.addNumber(number: thirdValue)
        
        // Then Operator Has Been Added To Result
        let actual = String(try expression.evaluate())
        let expected = String((firstValue + secondValue) * thirdValue)
        
        // Then Multiplication Is Correct
        XCTAssert(actual == expected)
    }
    
    func test_GivenArithmeticError_WhenSwitchOnAllCases_ThenRawValuesAndCasesAreCorresponding() throws {
        
        for arithmeticError in ArithmeticError.allCases {
            
            switch arithmeticError {
            case .DivisionByZero :
                XCTAssert(arithmeticError.rawValue == (Constants.titleDivisionByZero, Constants.divisionByZeroIsImpossible))
                let error = ArithmeticError.init(rawValue: (Constants.titleDivisionByZero, Constants.divisionByZeroIsImpossible))
                XCTAssert(arithmeticError == error)
            case .ExpressionIsIncorrect :
                XCTAssert(arithmeticError.rawValue == (Constants.titleExpressionIsIncorrect, Constants.expressionIsIncorrect))
                let error = ArithmeticError.init(rawValue: (Constants.titleExpressionIsIncorrect, Constants.expressionIsIncorrect))
                XCTAssert(arithmeticError == error)
            case .ExpressionHaveNotEnoughElement :
                XCTAssert(arithmeticError.rawValue == (Constants.titleExpressionHaveNotEnoughElement, Constants.expressionHaveNotEnoughElement))
                let error = ArithmeticError.init(rawValue: (Constants.titleExpressionHaveNotEnoughElement, Constants.expressionHaveNotEnoughElement))
                XCTAssert(arithmeticError == error)
            case .ExpressionHaveResult :
                XCTAssert(arithmeticError.rawValue == (Constants.titleExpressionHaveResult, Constants.expressionHaveResult))
                let error = ArithmeticError.init(rawValue: (Constants.titleExpressionHaveResult, Constants.expressionHaveResult))
                XCTAssert(arithmeticError == error)
            case .CannotAddOperator :
                XCTAssert(arithmeticError.rawValue == (Constants.titleCannotAddOperator, Constants.cannotAddOperator))
                let error = ArithmeticError.init(rawValue: (Constants.titleCannotAddOperator, Constants.cannotAddOperator))
                XCTAssert(arithmeticError == error)
            case .UnknownOperator :
                XCTAssert(arithmeticError.rawValue == (Constants.titleUnknownOperator, Constants.unknownOperator))
                let error = ArithmeticError.init(rawValue: (Constants.titleUnknownOperator, Constants.unknownOperator))
                XCTAssert(arithmeticError == error)
            case .None:
                XCTAssert(arithmeticError.rawValue == ("", ""))
                let error = ArithmeticError.init(rawValue: ("", ""))
                XCTAssert(arithmeticError == error)
            }
            
            switch arithmeticError.rawValue.message {
            case Constants.divisionByZeroIsImpossible:
                XCTAssert(arithmeticError == .DivisionByZero)
            case Constants.expressionIsIncorrect:
                XCTAssert(arithmeticError == .ExpressionIsIncorrect)
            case Constants.expressionHaveNotEnoughElement:
                XCTAssert(arithmeticError == .ExpressionHaveNotEnoughElement)
            case Constants.expressionHaveResult:
                XCTAssert(arithmeticError == .ExpressionHaveResult)
            case Constants.cannotAddOperator:
                XCTAssert(arithmeticError == .CannotAddOperator)
            case Constants.unknownOperator:
                XCTAssert(arithmeticError == .UnknownOperator)
            default:
                XCTAssert(arithmeticError == .None)
            }
        }
    }
    
    // **************** Testing ArithmeticElement **************** //
    
    func test_GivenArithmeticElement_WhenSwitchOnAllCases_ThenRawValuesAndCasesAreCorresponding() throws {
        
        for arithmeticElement in ArithmeticElement.allCases {
            
            switch arithmeticElement {
        
            case .addition:
                XCTAssert(arithmeticElement.rawValue == ("+"))
                XCTAssert(try arithmeticElement.evaluate(left: .number(firstValue), right: .number(secondValue)) == firstValue + secondValue)
            case .substraction:
                XCTAssert(arithmeticElement.rawValue == ("-"))
                XCTAssert(try arithmeticElement.evaluate(left: .number(firstValue), right: .number(secondValue)) == firstValue - secondValue)
            case .division:
                XCTAssert(arithmeticElement.rawValue == ("/"))
                XCTAssert(try arithmeticElement.evaluate(left: .number(firstValue), right: .number(secondValue)) == firstValue / secondValue)
            case .multiplication:
                XCTAssert(arithmeticElement.rawValue == ("*"))
                XCTAssert(try arithmeticElement.evaluate(left: .number(firstValue), right: .number(secondValue)) == firstValue * secondValue)
            case let .number(double):
                XCTAssert(arithmeticElement.rawValue == ("\(double)"))
                XCTAssert(try arithmeticElement.evaluate() == double)
            case  .evaluation:
                XCTAssert(arithmeticElement.rawValue == ("="))
                XCTAssert(try arithmeticElement.evaluate() == 0.0)
            default:
                XCTAssert(arithmeticElement.rawValue == (""))
                XCTAssert(try arithmeticElement.evaluate() == 0.0)
            }
        }
    }
}
