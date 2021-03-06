package com.trivago.rta.json.pojo;

import com.trivago.rta.constants.Status;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ElementTest {
    private Element element;

    @Before
    public void setup() {
        element = new Element();
    }

    @Test
    public void testGetSkippedStatusInEmptyElements() {
        Status status = element.getStatus();
        assertThat(status, is(Status.SKIPPED));
        assertThat(element.isSkipped(), is(true));
    }

    @Test
    public void testGetPassedStatus() {
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result result = new Result();
        result.setStatus("passed");
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);

        Status status = element.getStatus();
        assertThat(status, is(Status.PASSED));
        assertThat(element.isPassed(), is(true));
    }

    @Test
    public void testGetFailedStatus() {
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result result = new Result();
        result.setStatus("failed");
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);

        Status status = element.getStatus();
        assertThat(status, is(Status.FAILED));
        assertThat(element.isFailed(), is(true));
    }

    @Test
    public void testGetUndefinedStatus() {
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result result = new Result();
        result.setStatus("undefined");
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);

        Status status = element.getStatus();
        assertThat(status, is(Status.SKIPPED));
        assertThat(element.isSkipped(), is(true));
    }

    @Test
    public void totalDurationTest() {
        List<com.trivago.rta.json.pojo.Before> beforeSteps = new ArrayList<>();
        com.trivago.rta.json.pojo.Before before = new com.trivago.rta.json.pojo.Before();
        Result beforeResult = new Result();
        beforeResult.setDuration(1000000);
        before.setResult(beforeResult);
        beforeSteps.add(before);
        element.setBefore(beforeSteps);

        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result stepResult = new Result();
        stepResult.setDuration(9991000003L);
        step.setResult(stepResult);
        steps.add(step);

        Step step2 = new Step();
        Result stepResult2 = new Result();
        stepResult2.setDuration(123667782L);
        step2.setResult(stepResult2);
        steps.add(step2);

        element.setSteps(steps);

        List<com.trivago.rta.json.pojo.After> afterSteps = new ArrayList<>();
        com.trivago.rta.json.pojo.After after = new com.trivago.rta.json.pojo.After();
        Result afterResult = new Result();
        afterResult.setDuration(2000000);
        after.setResult(afterResult);
        afterSteps.add(after);
        element.setAfter(afterSteps);

        assertThat(element.getTotalDuration(), is(10117667785L));
        assertThat(element.returnTotalDurationString(), is("0m 10s 117ms"));
    }

    @Test
    public void stepSummaryTest() {
        List<Step> steps = new ArrayList<>();

        Step step1 = new Step();
        Result result1 = new Result();
        result1.setStatus("passed");
        step1.setResult(result1);
        steps.add(step1);
        steps.add(step1);
        steps.add(step1);

        Step step2 = new Step();
        Result result2 = new Result();
        result2.setStatus("skipped");
        step2.setResult(result2);
        steps.add(step2);

        Step step3 = new Step();
        Result result3 = new Result();
        result3.setStatus("pending");
        step3.setResult(result3);
        steps.add(step3);

        Step step4 = new Step();
        Result result4 = new Result();
        result4.setStatus("failed");
        step4.setResult(result4);
        steps.add(step4);

        element.setSteps(steps);

        assertThat(element.getTotalNumberOfSteps(), is(6));
        assertThat(element.getTotalNumberOfPassedSteps(), is(3));
        assertThat(element.getTotalNumberOfFailedSteps(), is(1));
        assertThat(element.getTotalNumberOfSkippedSteps(), is(2));
    }
}
