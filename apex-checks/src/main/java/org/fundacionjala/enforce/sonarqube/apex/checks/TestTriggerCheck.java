package org.fundacionjala.enforce.sonarqube.apex.checks;
import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;
import org.sonar.api.rule.RuleKey;
import org.sonar.check.Rule;
import org.sonar.squidbridge.checks.SquidCheck;

import static org.fundacionjala.enforce.sonarqube.apex.api.grammar.ApexGrammarRuleKey.CLASS_TRIGGER_DECLARATION;

import java.util.List;
/**
 * Created by fernando on 6/16/16.
 */
@Rule(key = TestTriggerCheck.CHECK_KEY)
public class TestTriggerCheck extends SquidCheck<Grammar> {

    public static final String CHECK_KEY = "T1001";

    private final String TRIGGER_MESSAGE = ChecksBundle.getStringFromBundle("TriggersForSameObject");

    @Override
    public void init(){
        subscribeTo(CLASS_TRIGGER_DECLARATION);
    }

    @Override
    public void visitNode(AstNode astNode) {
        List<AstNode> children = astNode.getChildren();
        getContext().createLineViolation(this, TRIGGER_MESSAGE, astNode);


    }

}
