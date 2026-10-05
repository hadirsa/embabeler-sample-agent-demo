package com.hadirsa.demo.plan;

import com.embabel.agent.test.integration.EmbabelMockitoIntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Fails the build when an agent's plan can never finish: no goal, an unreachable goal,
 * or a condition that no action declares in post. Works for Java and Kotlin agents alike,
 * because it reads the model Embabel builds, not the source code.
 */
class AgentPlanTest extends EmbabelMockitoIntegrationTest {

    @BeforeAll
    static void disableInteractiveShell() {
        System.setProperty("embabel.agent.shell.interactive.enabled", "false");
    }

    @Test
    void everyAgentCanReachItsGoal() {
        var findings = AgentPlanCheck.check(agentPlatform, "com.hadirsa.demo");
        System.out.println(AgentPlanCheck.report(findings));

        assertThat(AgentPlanCheck.errors(findings)).isEmpty();
    }
}
