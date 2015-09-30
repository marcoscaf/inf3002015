package br.com.unicamp.inf321.models.petclinic;


import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;

import br.com.unicamp.inf321.FindOwnersSharedState;
import br.com.unicamp.inf321.helper.Helper;

/**
 * Implements the model (and interface) FindOwnersSharedState
 * The default path generator is Random Path.
 * Stop condition is 100% coverage of all edges.
 */
@GraphWalker(value = "random(edge_coverage(100))")
public class FindOwners extends ExecutionContext implements FindOwnersSharedState {

    @Override
    public void v_Owners() {
        Assert.assertTrue(Helper.WaitForElement(By.tagName("h2")).getText().matches("Owners"));
    }

    @Override
    public void e_AddOwner() {
        Helper.WaitForElement(By.linkText("Add Owner")).click();
    }

    @Override
    public void v_FindOwners() {
        Assert.assertTrue(Helper.WaitForElement(By.tagName("h2")).getText().matches("Find Owners"));
    }

    @Override
    public void e_Search() {
        Helper.WaitForElement(By.cssSelector("button[type=\"submit\"]")).click();
    }

    @Override
    public void e_FindOwners() {
        Helper.WaitForElement(By.className("icon-search")).click();
    }

    @Override
    public void v_NewOwner() {
        Assert.assertTrue(Helper.WaitForElement(By.tagName("h2")).getText().matches("New Owner"));
    }
}
