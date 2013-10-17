package de.umg.mi.idrt.ioe.tos;

import java.util.Date;

import de.umg.mi.idrt.ioe.Application;
import de.umg.mi.idrt.ioe.Console;
import de.umg.mi.idrt.ioe.Resource;
import de.umg.mi.idrt.ioe.SystemMessage;
import de.umg.mi.idrt.ioe.OntologyTree.MyOntologyTree;
import de.umg.mi.idrt.ioe.OntologyTree.NodeType;
import de.umg.mi.idrt.ioe.OntologyTree.OntologyTree;
import de.umg.mi.idrt.ioe.OntologyTree.OntologyTreeNode;
import de.umg.mi.idrt.ioe.OntologyTree.OntologyTreeTargetRootNode;
import de.umg.mi.idrt.ioe.OntologyTree.Target;
import de.umg.mi.idrt.ioe.OntologyTree.TargetProject;
import de.umg.mi.idrt.ioe.OntologyTree.TargetProjects;
import de.umg.mi.idrt.ioe.view.OntologyEditorView;

/**
 * @author Christian Bauer
 *         <christian(dot)bauer(at)med(dot)uni-goettingen(dot)de> Department of
 *         Medical Informatics Goettingen www.mi.med.uni-goettingen.de
 * 
 *         interface that enables a talend open studio job to interact with the
 *         ioe a dummy copy of this has to be imported into the tos job
 */

public class TOSHandler {

	OntologyTree _ot = null;

	private OntologyTree _ontologyTreeTarget;
	
	public static final String TableIEOTargetOntology = "ioe_target_ontology";
	public static final String TableIEOTarget = "ioe_target";
	public static final String TableIEOTargetProject = "ioe_target_project";

	public TOSHandler() {

		boolean nullError = false;

		// try to get the current ot from the view
		 if (OntologyEditorView.getI2b2ImportTool() == null) {
			nullError = true;
			System.out
					.println("Application.getEditorSourceView().getI2B2ImportTool() == null");
		} else if (OntologyEditorView.getI2b2ImportTool().getMyOntologyTrees() == null) {
			nullError = true;
			System.out
					.println("Application.getEditorSourceView().getI2B2ImportTool().getMyOT() == null");
		} else if (OntologyEditorView.getI2b2ImportTool().getMyOntologyTrees()
				.getOntologyTreeSource() == null) {
			nullError = true;
			System.out
					.println("Application.getEditorSourceView().getI2B2ImportTool().getMyOT().getOT() == null");
		}

		if (nullError == true) {
			Console.error("Could not find a working OntologyTree to add data.");
			System.out
					.println("Could not find a working OntologyTree to add data.");
			return;
		}

		MyOntologyTree _myOT = OntologyEditorView.getI2b2ImportTool()
				.getMyOntologyTrees();
		_ot = _myOT.getOntologyTreeSource();
		/*
		 * _otCreator = OntologyEditorView.getI2b2ImportTool() .getOTCreator();
		 */
		_ontologyTreeTarget = _myOT.getOntologyTreeTarget();
	}

	public static void status(String status) {
		Console.info("TOS: " + status);
	}

	public static void statusSuccess(String status) {
		Application.getStatusView().addMessage(
				new SystemMessage(status, SystemMessage.MessageType.SUCCESS));

	}

	public static void statusError(String status) {
		Console.error("TOS error:" + status);
		Application.getStatusView().addMessage(
				new SystemMessage(status, SystemMessage.MessageType.ERROR));
	}

	public static void addi2b2OntologyItemToTree(int C_HLEVEL,
			String C_FULLNAME, String C_NAME, String C_SYNONYM_CD,
			String C_VISUALATTRIBUTES, int C_TOTALNUM, String C_BASECODE,
			String C_METADATAXML, String C_FACTTABLECOLUMN, String C_TABLENAME,
			String C_COLUMNNAME, String C_COLUMNDATATYPE, String C_OPERATOR,
			String C_DIMCODE, String C_COMMENT, String C_TOOLTIP,
			String M_APPLIED_PATH, Date UPDATE_DATE, Date DOWNLOAD_DATE,
			Date IMPORT_DATE, String SOURCESYSTEM_CD, String VALUETYPE_CD,
			String M_EXCLUSION_CD, String C_PATH, String C_SYMBOL) {

		OntologyTree _ot = OntologyEditorView.getI2b2ImportTool()
				.getMyOntologyTrees().getOntologyTreeSource();

		if (_ot == null)
			System.out.println("_ot == null!!");

		// System.out.println("C_METADATAXML: ");
		// System.out.println(" - link1: " + String.valueOf(C_METADATAXML));
		// System.out.println(" - link2: " + C_METADATAXML != null ?
		// String.valueOf(C_METADATAXML) : "<null>");

		if (C_HLEVEL < 3) {
			System.out.println(" node " + C_NAME + " ");
			System.out.println("   -> " + C_HLEVEL + " -> " + C_FULLNAME);
		}

		OntologyTreeNode node = _ot.addNodeByPath(C_FULLNAME, C_NAME);
		node.setType(Resource.I2B2.NODE.TYPE.ONTOLOGY_SOURCE);
		node.setOntologyCellAttributes(C_HLEVEL, C_FULLNAME, C_NAME,
				C_SYNONYM_CD, C_VISUALATTRIBUTES, C_TOTALNUM, C_BASECODE,
				C_METADATAXML, C_FACTTABLECOLUMN, C_TABLENAME, C_COLUMNNAME,
				C_COLUMNDATATYPE, C_OPERATOR, C_DIMCODE, C_COMMENT, C_TOOLTIP,
				M_APPLIED_PATH, UPDATE_DATE, DOWNLOAD_DATE, IMPORT_DATE,
				SOURCESYSTEM_CD, VALUETYPE_CD, M_EXCLUSION_CD, C_PATH, C_SYMBOL);

		if (C_HLEVEL == 0) {
			node.setNodeType(NodeType.I2B2ROOT);
		}

	}

	public void addi2b2ConceptDimensionItemToTree(boolean isConceptDimension,
			String dimensionPath, String dimensionCD, String nameChar,
			String dimensionBlob, Date updateDate, Date downloadDate,
			Date importDate, String sourcesysteCD, String uploadID) {
		// implement in main program
	}

	public void readTargetOntology(int treeLevel, String treePath,
			String sourcePath, String name, int changed,
			String startdateSourcePath, String enddateSourcePath,
			String visualattributes) {

		OntologyTreeNode node = _ontologyTreeTarget.addNodeByPath(treePath,
				name);
		node.setType(Resource.I2B2.NODE.TYPE.ONTOLOGY_TARGET);
		node.getTargetNodeAttributes().setSourcePath(sourcePath);
		node.getTargetNodeAttributes().setName(name);
		node.getTargetNodeAttributes()
				.setChanged((changed == 0 ? true : false));
		node.getTargetNodeAttributes().setStartDateSourcePath(
				startdateSourcePath);
		node.getTargetNodeAttributes().setEndDateSourcePath(enddateSourcePath);
		node.getTargetNodeAttributes().setVisualattributes(visualattributes);

	}

	public void writeTargetOntology(int targetID, String treeNodePath,
			int treeLevel, String sourceNodePath, int changed,
			String startdateSourcePath, String enddateSourcePath) {

	}

	public static void  addTargetProjectToTargetProjects(int id,
			String name, String description) {

		TargetProject targetProject = new TargetProject();
		targetProject.setTargetProjectID(id);
		targetProject.setName(name);
		targetProject.setDescription(description);
		
		OntologyTreeTargetRootNode targetRootNode = ((OntologyTreeTargetRootNode) OntologyEditorView
				.getI2b2ImportTool().getMyOntologyTrees()
				.getOntologyTreeTarget().getRootNode());

		TargetProjects targetProjects = targetRootNode.getTargetProjects();
		targetProjects.add(targetProject);
		
	}

	public static void addTargetVersionToTargeProject(int targetID,
			int targetProjectID, int version, Date created, Date lastModified,
			String targetDBSchema) {

		Target target = new Target();
		target.setTargetID(targetID);
		target.setTargetProjectID(targetProjectID);
		target.setVersion(version);
		target.setCreated(created);
		target.setLastModified(lastModified);
		target.setTargetDBSchema(targetDBSchema);
		OntologyTreeTargetRootNode targetRootNode = new OntologyTreeTargetRootNode(
				"");
		
		targetRootNode = ((OntologyTreeTargetRootNode) OntologyEditorView
				.getI2b2ImportTool().getMyOntologyTrees()
				.getOntologyTreeTarget().getRootNode());

		TargetProjects targetProjects = targetRootNode.getTargetProjects();
		targetProjects.addTarget(target);

	}

}
