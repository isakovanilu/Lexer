
import java.util.List;

public class Block extends ASTNode {
    public List<ASTNode> statements;

    Block(List<ASTNode> statements) {
        this.statements = statements;
    }
    @Override
    public void print(String indent) {
        for (ASTNode node: statements) {
            node.print(" ");
        }

    }
}
