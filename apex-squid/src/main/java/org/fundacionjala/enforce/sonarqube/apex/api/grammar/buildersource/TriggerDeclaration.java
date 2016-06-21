/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.enforce.sonarqube.apex.api.grammar.buildersource;

import org.sonar.sslr.grammar.LexerfulGrammarBuilder;

import static com.sonar.sslr.api.GenericTokenType.IDENTIFIER;
import static org.fundacionjala.enforce.sonarqube.apex.api.ApexKeyword.*;
import static org.fundacionjala.enforce.sonarqube.apex.api.ApexPunctuator.*;
import static org.fundacionjala.enforce.sonarqube.apex.api.grammar.ApexGrammarRuleKey.*;

import static com.sonar.sslr.api.GenericTokenType.EOF;

/**
 * This class contains constructors for Declaration rules and its sub rules.
 *
 */
public class TriggerDeclaration {
    public static void create(LexerfulGrammarBuilder grammarBuilder) {
        classTriggerDeclaration(grammarBuilder);
        classTriggerName(grammarBuilder);
        objectTriggerName(grammarBuilder);
        triggerEvent(grammarBuilder);
        triggerAction(grammarBuilder);
    }

    private static void triggerEvent(LexerfulGrammarBuilder grammarBuilder) {
        grammarBuilder.rule(TRIGGER_EVENT).is(grammarBuilder.zeroOrMore(
                grammarBuilder.firstOf(
                        BEFORE,
                        AFTER))
        );
    }
    private static void triggerAction(LexerfulGrammarBuilder grammarBuilder) {
        grammarBuilder.rule(TRIGGER_ACTION).is(grammarBuilder.zeroOrMore(
                grammarBuilder.firstOf(
                        INSERT,
                        UPDATE,
                        DELETE,
                        UNDELETE))
        );
    }

    /**
     * It is responsible for managing the method name.
     *
     * @param grammarBuilder ApexGrammarBuilder parameter.
     */
    private static void classTriggerName(LexerfulGrammarBuilder grammarBuilder) {
        grammarBuilder.rule(CLASS_TRIGGER_IDENTIFIER).is(
                IDENTIFIER);
    }
    private static void objectTriggerName(LexerfulGrammarBuilder grammarBuilder) {
        grammarBuilder.rule(OBJECT_TRIGGER_NAME).is(
                IDENTIFIER);
    }
    private static void classTriggerDeclaration(LexerfulGrammarBuilder grammarBuilder) {
        grammarBuilder.rule(CLASS_TRIGGER_DECLARATION).is(
                TRIGGER,
                CLASS_TRIGGER_IDENTIFIER,
                ON,
                OBJECT_TRIGGER_NAME,
                LPAREN,
                TRIGGER_EVENT,
                TRIGGER_ACTION,
                grammarBuilder.zeroOrMore(COMMA, TRIGGER_EVENT,TRIGGER_ACTION),
                RPAREN,
                LBRACE,
                RBRACE);
    }

}