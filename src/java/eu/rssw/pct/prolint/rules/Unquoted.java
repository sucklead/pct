package eu.rssw.pct.prolint.rules;

import java.io.File;

import org.prorefactor.core.JPNode;
import org.prorefactor.treeparser.ParseUnit;

import com.joanju.proparse.NodeTypes;

import eu.rssw.pct.prolint.AbstractLintRule;
import eu.rssw.pct.prolint.ILintCallback;
import eu.rssw.pct.prolint.LintWarning;

public class Unquoted extends AbstractLintRule {

    public Unquoted() {
        super("unquoted", "Bug", 5 , "Find unquoted string literals");
    }

    public void execute(ParseUnit unit, File xref, ILintCallback callback) {
        for (JPNode node : unit.getTopNode().query(NodeTypes.FILENAME)) {
            JPNode headNode = node.parent().getStatement();
            JPNode prevNode = node.prevSibling();

            if ((headNode.getType() != NodeTypes.RUN)
                    || ((prevNode != null) && (prevNode.getType() != NodeTypes.RUN))) {
                char c = node.getText().charAt(0);
                if ((c != '"') && (c != '\'')) {
                    LintWarning warning = new LintWarning(getRuleName(), getCategory(), getSeverity(), new File(
                            node.getFilename()));
                    warning.setLine(node.getLine());
                    warning.setCol(node.getColumn());
                    warning.setMsg("Unquoted string " + node.getText());
                    callback.publishWarning(warning);
                }
            }
        }

        for (JPNode node : unit.getTopNode().query(NodeTypes.UNQUOTEDSTRING)) {
            LintWarning warning = new LintWarning(getRuleName(), getCategory(), getSeverity(), new File(
                    node.getFilename()));
            warning.setLine(node.getLine());
            warning.setCol(node.getColumn());
            warning.setMsg("Unquoted string : " + node.getText());
            callback.publishWarning(warning);
        }
    }

}
