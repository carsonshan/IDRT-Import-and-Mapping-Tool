package de.umg.mi.idrt.ioe.OntologyTree;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import de.umg.mi.idrt.ioe.ActionCommand;
import de.umg.mi.idrt.ioe.Application;
import de.umg.mi.idrt.ioe.Console;
import de.umg.mi.idrt.ioe.Resource;

/**
 * @author Christian Bauer
 *         <christian(dot)bauer(at)med(dot)uni-goettingen(dot)de> Department of
 *         Medical Informatics Goettingen www.mi.med.uni-goettingen.de
 * 
 *         drop listener for getting data from the source tree to the target
 *         tree
 * 
 */

public class NodeDropListener extends ViewerDropAdapter {

	private MyOntologyTree myOT = null;
	private final Viewer viewer;
	private OntologyTreeNode sourceNode = null;
	private OntologyTreeNode targetNode = null;

	public NodeDropListener(Viewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void drop(DropTargetEvent event) {
		int location = this.determineLocation(event);
		// String target = (String) determineTarget(event);

		String translatedLocation = "";
		Console.info("getDate: "
				+ ((OntologyTreeNode) event.item.getData()).getName());
		event.item.getData();
		OntologyTreeNode targetNode = (OntologyTreeNode) determineTarget(event);

		this.targetNode = targetNode;

		switch (location) {
		case 1:
			translatedLocation = "Dropped before the target ";
			break;
		case 2:
			translatedLocation = "Dropped after the target ";
			break;
		case 3:
			translatedLocation = "Dropped on the target ";
			break;
		case 4:
			translatedLocation = "Dropped into nothing ";
			break;
		}

		Console.info(translatedLocation);

		if (location != 4) {
			Console.info("The drop was done on the element: "
					+ targetNode.getName());

			/* copy nodes */

		}
		super.drop(event);
	}

	// This method performs the actual drop
	// We simply add the String we receive to the model and trigger a refresh of
	// the
	// viewer by calling its setInput method.
	@Override
	public boolean performDrop(Object data) {
		Console.info("Dropped performed with data:");
		// Console.info(" - selectedObjectClass: " +
		// this..getSelectedObject().getClass().getSimpleName());
		Console.info(" - data: " + data.getClass().getSimpleName());
		Console.info(" - data_toString: " + (String) data.toString());

		if (this.targetNode != null) {
			Console.info(" - targetNode: " + targetNode.getName());
		} else {
			Console.info(" - targetNode is Null!");
		}

		Console.info(" - data this.myOT?: "
				+ (this.myOT == null ? "isNull" : "is NOT null"));
		Console.info(" - data this.myOT.getViewTree()?: "
				+ (this.myOT.getVieTreeSource() == null ? "isNull"
						: "is NOT null"));

		Console.info(" - data size?: "
				+ this.myOT.getVieTreeSource().stringPathToViewTreeNode.size());
		System.out
				.println(" - data size2?: "
						+ this.myOT.getViewTreeTarget().stringPathToViewTreeNode
								.size());

		/*
		 * ActionCommand command2 = new
		 * ActionCommand(Resource.ID.OntologyTreeEditor.Command.EXPORT);
		 * command2.addParameter(Resource.ID.Command.
		 * EXPORT_ATTRIBUTE_RESTRICT_NUMBER_OF_PATIENTS, Integer.valueOf(2));
		 * command2
		 * .addParameter(Resource.ID.Command.EXPORT_ATTRIBUTE_NUMBER_OF_PATIENTS
		 * , "3");
		 * 
		 * Application.executeCommand(command2);
		 */

		Console.info("OTCOPY:");

		Console.info(" - things to copy: SourceNode (\""
				+ data.toString() + "\") or TargetNode (\""
				+ targetNode.getTreePath() + "\") ");

		myOT.dropCommandCopyNodes(data.toString(), targetNode.getTreePath());

		// OTNode sourceNode = this.myOT.getOT().getNodeLists().getNodeByPath(
		// data.toString() );

		/*
		 * 
		 * if ( sourceNode != null ){ Console.info("sourceNode found:" +
		 * sourceNode.getName()); this.myOT.copySourceNodeToTarget(sourceNode,
		 * targetNode); //targetNode.get
		 * //myOT.getOTTarget().getTreeViewer().expandToLevel(targetNode,
		 * level); } else {
		 * Console.info("Eorr in NodeDropListener: SourceNode (\"" +
		 * data.toString() + "\") not found."); }
		 */

		// fileSelectionButton.setFocus();

		this.viewer.refresh();

		return false;
	}

	@Override
	public boolean validateDrop(Object target, int operation,
			TransferData transferType) {
		return true;

	}

	public void setMyOT(MyOntologyTree myOT) {
		this.myOT = myOT;
	}
}