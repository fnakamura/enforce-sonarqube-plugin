/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.enforce.sonarqube.apex.parser.grammar;

import org.fundacionjala.enforce.sonarqube.apex.parser.ApexRuleTest;
import org.junit.Before;
import org.junit.Test;

import static org.fundacionjala.enforce.sonarqube.apex.api.grammar.ApexGrammarRuleKey.CLASS_TRIGGER_DECLARATION;
import static org.sonar.sslr.tests.Assertions.assertThat;

public class ApexGrammarTriggerClassDeclarationTest extends ApexRuleTest {

    @Before
    public void init() {
        setRootRule(CLASS_TRIGGER_DECLARATION);
    }

    @Test
    public void positiveRules() {
        assertThat(parser)
                .matches("trigger isMethod on Ojbect(before insert){}")
                .matches("trigger isMethod on Ojbect(before update){}")
                .matches("trigger isMethod on Ojbect(before delete){}")
                .matches("trigger isMethod on Ojbect(after insert){}")
                .matches("trigger isMethod on Ojbect(after update){}")
                .matches("trigger isMethod on Ojbect(after delete){}")
                .matches("trigger isMethod on Ojbect(after undelete){}")
                .matches("trigger isMethod on Ojbect(after undelete, after update, before insert){}");
    }
    @Test
    public void negativeRules() {
        assertThat(parser)
                .notMatches("trigger isMethod on (before insert){}")
                .notMatches("trigger isMethod on Object(before insert)");
    }
}
