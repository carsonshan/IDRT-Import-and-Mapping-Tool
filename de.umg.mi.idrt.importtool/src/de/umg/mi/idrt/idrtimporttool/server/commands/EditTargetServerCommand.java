package de.umg.mi.idrt.idrtimporttool.server.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.TreeItem;

import de.umg.mi.idrt.idrtimporttool.server.Settings.I2b2Project;
import de.umg.mi.idrt.idrtimporttool.server.Settings.Server;
import de.umg.mi.idrt.idrtimporttool.server.Settings.ServerList;
import de.umg.mi.idrt.idrtimporttool.server.serverWizard.EditServerWizard;
import de.umg.mi.idrt.importtool.views.ServerView;

/**
 * @author Benjamin Baum <benjamin(dot)baum(at)med(dot)uni-goettingen(dot)de>
 *         Department of Medical Informatics Goettingen
 *         www.mi.med.uni-goettingen.de
 */
public class EditTargetServerCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		TreeViewer viewer = ServerView.getTargetServersViewer();

		if (viewer.getTree().getSelection().length > 0) {
			TreeItem currentTreeItem = viewer.getTree().getSelection()[0];
			String serverUniqueID = currentTreeItem.getText();

			if (currentTreeItem.getData() instanceof Server) {
				Server currentServer = ServerList.getTargetServers().get(
						serverUniqueID);
				WizardDialog wizardDialog = new WizardDialog(viewer
						.getControl().getShell(), new EditServerWizard(
						currentServer, true,"target"));
				
				wizardDialog.open();
			} else 	if (currentTreeItem.getData() instanceof I2b2Project) {
				TreeItem parentTreeitem = currentTreeItem.getParentItem();
				serverUniqueID = parentTreeitem.getText();
				Server currentServer = ServerList.getTargetServers().get(
						serverUniqueID);

				WizardDialog wizardDialog = new WizardDialog(viewer
						.getControl().getShell(), new EditServerWizard(
						currentServer, true,"target"));
				wizardDialog.open();
			}

		}
		viewer.refresh();
		return viewer;
	}
}
