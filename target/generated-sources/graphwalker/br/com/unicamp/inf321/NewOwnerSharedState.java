// Generated by GraphWalker (http://www.graphwalker.org)
package br.com.unicamp.inf321;

import org.graphwalker.java.annotation.Model;
import org.graphwalker.java.annotation.Vertex;
import org.graphwalker.java.annotation.Edge;

@Model(file = "br/com/unicamp/inf321/NewOwnerSharedState.graphml")
public interface NewOwnerSharedState {

    @Vertex()
    void v_OwnerInformation();

    @Edge()
    void e_CorrectData();

    @Edge()
    void e_IncorrectData();

    @Vertex()
    void v_IncorrectData();

    @Vertex()
    void v_NewOwner();
}
