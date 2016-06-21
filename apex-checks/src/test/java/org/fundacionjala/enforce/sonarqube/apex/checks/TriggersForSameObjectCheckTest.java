/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.enforce.sonarqube.apex.checks;

import org.junit.Before;
import org.junit.Test;
import org.sonar.squidbridge.api.SourceFile;
import org.sonar.squidbridge.checks.CheckMessagesVerifier;

import java.io.File;

import static org.fundacionjala.enforce.sonarqube.apex.ApexAstScanner.scanFile;

public class TriggersForSameObjectCheckTest {

    private TestTriggerCheck testTriggerCheck;
    private SourceFile sourceFile;


    public void TriggersForSameObjectCheckTest() {


    }

    @Test
    public void testTriggersForSameObject() {
        testTriggerCheck = new TestTriggerCheck();
        sourceFile = scanFile(new File("src/test/resources/checks/triggersForSameObject.cls"), testTriggerCheck);
        CheckMessagesVerifier.verify(sourceFile.getCheckMessages())
                .next().atLine(1).withMessage("Triggers for same object.")
                .noMore();
    }
}
