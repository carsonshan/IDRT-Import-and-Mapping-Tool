// ============================================================================
//
// Copyright (c) 2006-2013, Talend Inc.
//
// This source code has been automatically generated by_Talend Open Studio for Data Integration
// / Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package tos.idrt_truncate_tables_0_1;

import routines.IIT;
import routines.Mathematical;
import routines.DataOperation;
import routines.Relational;
import routines.TalendDate;
import routines.TalendDataGenerator;
import routines.Numeric;
import routines.enc_num_routine;
import routines.TalendString;
import routines.StringHandling;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

//the import part of tLibraryLoad_1
import de.goettingen.i2b2.importtool.idrt.StatusListener.StatusListener;

//the import part of tJava_1
//import java.util.List;

//the import part of tJava_2
//import java.util.List;

@SuppressWarnings("unused")
/**
 * Job: IDRT_Truncate_Tables Purpose: <br>
 * Description:  <br>
 * @author test@talend.com
 * @version 5.3.1.r104014
 * @status 
 */
public class IDRT_Truncate_Tables implements TalendJob {

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset
			.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends java.util.Properties {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

			if (DBInstance != null) {

				this.setProperty("DBInstance", DBInstance.toString());

			}

			if (DBHost != null) {

				this.setProperty("DBHost", DBHost.toString());

			}

			if (DBPort != null) {

				this.setProperty("DBPort", DBPort.toString());

			}

			if (project != null) {

				this.setProperty("project", project.toString());

			}

			if (DBPassword != null) {

				this.setProperty("DBPassword", DBPassword.toString());

			}

			if (DBSchema != null) {

				this.setProperty("DBSchema", DBSchema.toString());

			}

			if (DBUsername != null) {

				this.setProperty("DBUsername", DBUsername.toString());

			}

		}

		public String DBInstance;

		public String getDBInstance() {
			return this.DBInstance;
		}

		public String DBHost;

		public String getDBHost() {
			return this.DBHost;
		}

		public String DBPort;

		public String getDBPort() {
			return this.DBPort;
		}

		public String project;

		public String getProject() {
			return this.project;
		}

		public java.lang.String DBPassword;

		public java.lang.String getDBPassword() {
			return this.DBPassword;
		}

		public String DBSchema;

		public String getDBSchema() {
			return this.DBSchema;
		}

		public String DBUsername;

		public String getDBUsername() {
			return this.DBUsername;
		}
	}

	private ContextProperties context = new ContextProperties();

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "IDRT_Truncate_Tables";
	private final String projectName = "TOS";
	public Integer errorCode = null;
	private String currentComponent = "";
	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	public boolean isExportedAsOSGI = false;

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	public void setDataSources(
			java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources
				.entrySet()) {
			talendDataSources.put(
					dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry
							.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(
			new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private java.lang.Exception exception = null;

	public java.lang.Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends java.lang.Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private java.lang.Exception e = null;
		private String currentComponent = null;

		private TalendException(java.lang.Exception e, String errorComponent,
				final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public java.lang.Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				globalMap.put(currentComponent + "_ERROR_MESSAGE",
						e.getMessage());
				System.err
						.println("Exception in component " + currentComponent);
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					IDRT_Truncate_Tables.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass()
							.getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(IDRT_Truncate_Tables.this, new Object[] {
									e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (java.lang.SecurityException e) {
					this.e.printStackTrace();
				} catch (java.lang.IllegalArgumentException e) {
					this.e.printStackTrace();
				} catch (java.lang.IllegalAccessException e) {
					this.e.printStackTrace();
				} catch (java.lang.reflect.InvocationTargetException e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tLibraryLoad_1_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tLibraryLoad_1", System.currentTimeMillis());

		status = "failure";

		tLibraryLoad_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJava_1_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tJava_1", System.currentTimeMillis());

		status = "failure";

		tJava_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tOracleConnection_1_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tOracleConnection_1", System.currentTimeMillis());

		status = "failure";

		tOracleConnection_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tForeach_1_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tForeach_1", System.currentTimeMillis());

		status = "failure";

		tForeach_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tOracleRow_1_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tOracleRow_1", System.currentTimeMillis());

		try {

			errorCode = null;
			tJava_2Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}

		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		tForeach_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJava_2_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tJava_2", System.currentTimeMillis());

		status = "failure";

		tJava_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLibraryLoad_1_onSubJobError(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tJava_1_onSubJobError(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tOracleConnection_1_onSubJobError(
			java.lang.Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tForeach_1_onSubJobError(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tJava_2_onSubJobError(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tLibraryLoad_1Process(
			final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tLibraryLoad_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";
		int iterateLoop = 0;
		String currentComponent = "";

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				/**
				 * [tLibraryLoad_1 begin ] start
				 */

				ok_Hash.put("tLibraryLoad_1", false);
				start_Hash.put("tLibraryLoad_1", System.currentTimeMillis());
				currentComponent = "tLibraryLoad_1";

				int tos_count_tLibraryLoad_1 = 0;

				/**
				 * [tLibraryLoad_1 begin ] stop
				 */
				/**
				 * [tLibraryLoad_1 main ] start
				 */

				currentComponent = "tLibraryLoad_1";

				tos_count_tLibraryLoad_1++;

				/**
				 * [tLibraryLoad_1 main ] stop
				 */
				/**
				 * [tLibraryLoad_1 end ] start
				 */

				currentComponent = "tLibraryLoad_1";

				ok_Hash.put("tLibraryLoad_1", true);
				end_Hash.put("tLibraryLoad_1", System.currentTimeMillis());

				/**
				 * [tLibraryLoad_1 end ] stop
				 */

			}// end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil
						.addLog("CHECKPOINT",
								"CONNECTION:SUBJOB_OK:tLibraryLoad_1:OnSubjobOk",
								"", Thread.currentThread().getId() + "", "",
								"", "", "", "");
			}

			tJava_1Process(globalMap);

		} catch (java.lang.Exception e) {

			throw new TalendException(e, currentComponent, globalMap);

		} catch (java.lang.Error error) {

			throw error;

		}

		globalMap.put("tLibraryLoad_1_SUBPROCESS_STATE", 1);
	}

	public void tJava_1Process(final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tJava_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";
		int iterateLoop = 0;
		String currentComponent = "";

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				/**
				 * [tJava_1 begin ] start
				 */

				ok_Hash.put("tJava_1", false);
				start_Hash.put("tJava_1", System.currentTimeMillis());
				currentComponent = "tJava_1";

				int tos_count_tJava_1 = 0;

				System.out.println("Truncating Tables for schema: "
						+ context.DBSchema);

				/**
				 * [tJava_1 begin ] stop
				 */
				/**
				 * [tJava_1 main ] start
				 */

				currentComponent = "tJava_1";

				tos_count_tJava_1++;

				/**
				 * [tJava_1 main ] stop
				 */
				/**
				 * [tJava_1 end ] start
				 */

				currentComponent = "tJava_1";

				ok_Hash.put("tJava_1", true);
				end_Hash.put("tJava_1", System.currentTimeMillis());

				/**
				 * [tJava_1 end ] stop
				 */

			}// end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT",
						"CONNECTION:SUBJOB_OK:tJava_1:OnSubjobOk", "", Thread
								.currentThread().getId() + "", "", "", "", "",
						"");
			}

			tOracleConnection_1Process(globalMap);

		} catch (java.lang.Exception e) {

			throw new TalendException(e, currentComponent, globalMap);

		} catch (java.lang.Error error) {

			throw error;

		}

		globalMap.put("tJava_1_SUBPROCESS_STATE", 1);
	}

	public void tOracleConnection_1Process(
			final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tOracleConnection_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";
		int iterateLoop = 0;
		String currentComponent = "";

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				/**
				 * [tOracleConnection_1 begin ] start
				 */

				ok_Hash.put("tOracleConnection_1", false);
				start_Hash.put("tOracleConnection_1",
						System.currentTimeMillis());
				currentComponent = "tOracleConnection_1";

				int tos_count_tOracleConnection_1 = 0;

				String url_tOracleConnection_1 = "jdbc:oracle:thin:@"
						+ context.DBHost + ":" + context.DBPort + ":"
						+ context.DBInstance;
				globalMap.put("connectionType_" + "tOracleConnection_1",
						"ORACLE_SID");

				String userName_tOracleConnection_1 = context.DBUsername;
				String password_tOracleConnection_1 = context.DBPassword;

				java.sql.Connection conn_tOracleConnection_1 = null;

				if ((null == globalMap.get(KEY_DB_DATASOURCES))
						|| "".equals("")) {

					java.lang.Class.forName("oracle.jdbc.OracleDriver");

					conn_tOracleConnection_1 = java.sql.DriverManager
							.getConnection(url_tOracleConnection_1,
									userName_tOracleConnection_1,
									password_tOracleConnection_1);
					globalMap.put("conn_tOracleConnection_1",
							conn_tOracleConnection_1);
				}
				if (null != conn_tOracleConnection_1) {

					conn_tOracleConnection_1.setAutoCommit(true);
				}
				globalMap.put("host_" + "tOracleConnection_1", context.DBHost);
				globalMap.put("port_" + "tOracleConnection_1", context.DBPort);
				globalMap.put("dbname_" + "tOracleConnection_1",
						context.DBInstance);

				globalMap.put("conn_" + "tOracleConnection_1",
						conn_tOracleConnection_1);
				globalMap.put("dbschema_" + "tOracleConnection_1",
						context.DBSchema);
				globalMap.put("username_" + "tOracleConnection_1",
						context.DBUsername);
				globalMap.put("password_" + "tOracleConnection_1",
						context.DBPassword);

				/**
				 * [tOracleConnection_1 begin ] stop
				 */
				/**
				 * [tOracleConnection_1 main ] start
				 */

				currentComponent = "tOracleConnection_1";

				tos_count_tOracleConnection_1++;

				/**
				 * [tOracleConnection_1 main ] stop
				 */
				/**
				 * [tOracleConnection_1 end ] start
				 */

				currentComponent = "tOracleConnection_1";

				ok_Hash.put("tOracleConnection_1", true);
				end_Hash.put("tOracleConnection_1", System.currentTimeMillis());

				/**
				 * [tOracleConnection_1 end ] stop
				 */

			}// end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT",
						"CONNECTION:SUBJOB_OK:tOracleConnection_1:OnSubjobOk",
						"", Thread.currentThread().getId() + "", "", "", "",
						"", "");
			}

			tForeach_1Process(globalMap);

		} catch (java.lang.Exception e) {

			throw new TalendException(e, currentComponent, globalMap);

		} catch (java.lang.Error error) {

			throw error;

		}

		globalMap.put("tOracleConnection_1_SUBPROCESS_STATE", 1);
	}

	public void tForeach_1Process(final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tForeach_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";
		int iterateLoop = 0;
		String currentComponent = "";

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				/**
				 * [tForeach_1 begin ] start
				 */

				int NB_ITERATE_tOracleRow_1 = 0; // for statistics

				ok_Hash.put("tForeach_1", false);
				start_Hash.put("tForeach_1", System.currentTimeMillis());
				currentComponent = "tForeach_1";

				int tos_count_tForeach_1 = 0;

				Object[] values_tForeach_1 = new Object[] {
						"TRUNCATE TABLE " + context.DBSchema + ".I2B2",
						"TRUNCATE TABLE " + context.DBSchema
								+ ".CONCEPT_DIMENSION",
						"TRUNCATE TABLE " + context.DBSchema
								+ ".MODIFIER_DIMENSION",
						"TRUNCATE TABLE " + context.DBSchema
								+ ".OBSERVATION_FACT",
						"TRUNCATE TABLE " + context.DBSchema
								+ ".PATIENT_DIMENSION",
						"TRUNCATE TABLE " + context.DBSchema
								+ ".PATIENT_MAPPING",
						"TRUNCATE TABLE " + context.DBSchema
								+ ".MASTER_QUERY_GLOBAL_TEMP",
						"TRUNCATE TABLE " + context.DBSchema
								+ ".QT_PATIENT_SET_COLLECTION",
						"TRUNCATE TABLE " + context.DBSchema
								+ ".QT_PDO_QUERY_MASTER",
						"TRUNCATE TABLE " + context.DBSchema + ".QT_XML_RESULT",
						"TRUNCATE TABLE " + context.DBSchema
								+ ".ENCOUNTER_MAPPING",
						"update "
								+ context.DBSchema
								+ ".qt_query_result_instance set delete_flag='Y'",
						"update " + context.DBSchema
								+ ".qt_query_instance set delete_flag='Y'",
						"update " + context.DBSchema
								+ ".qt_query_master set delete_flag='Y'", };

				for (Object tmp_tForeach_1 : values_tForeach_1) {
					globalMap.put("tForeach_1_CURRENT_VALUE", tmp_tForeach_1);

					/**
					 * [tForeach_1 begin ] stop
					 */
					/**
					 * [tForeach_1 main ] start
					 */

					currentComponent = "tForeach_1";

					tos_count_tForeach_1++;

					/**
					 * [tForeach_1 main ] stop
					 */
					NB_ITERATE_tOracleRow_1++;
					iterateLoop++;

					/**
					 * [tOracleRow_1 begin ] start
					 */

					ok_Hash.put("tOracleRow_1", false);
					start_Hash.put("tOracleRow_1", System.currentTimeMillis());
					currentComponent = "tOracleRow_1";

					int tos_count_tOracleRow_1 = 0;

					java.sql.Connection conn_tOracleRow_1 = null;
					conn_tOracleRow_1 = (java.sql.Connection) globalMap
							.get("conn_tOracleConnection_1");
					if (null == conn_tOracleRow_1) {
						java.util.Map<String, routines.system.TalendDataSource> dataSources_tOracleRow_1 = (java.util.Map<String, routines.system.TalendDataSource>) globalMap
								.get(KEY_DB_DATASOURCES);
						conn_tOracleRow_1 = dataSources_tOracleRow_1.get("")
								.getConnection();
					}

					java.sql.Statement stmt_tOracleRow_1 = conn_tOracleRow_1
							.createStatement();
					String query_tOracleRow_1 = "";
					boolean whetherReject_tOracleRow_1 = false;

					/**
					 * [tOracleRow_1 begin ] stop
					 */
					/**
					 * [tOracleRow_1 main ] start
					 */

					currentComponent = "tOracleRow_1";

					query_tOracleRow_1 = ((String) globalMap
							.get("tForeach_1_CURRENT_VALUE"));
					whetherReject_tOracleRow_1 = false;

					globalMap.put("tOracleRow_1_QUERY", query_tOracleRow_1);
					try {
						stmt_tOracleRow_1.execute(query_tOracleRow_1);

					} catch (java.lang.Exception e) {
						whetherReject_tOracleRow_1 = true;
						throw (e);
					}

					if (!whetherReject_tOracleRow_1) {
					}

					tos_count_tOracleRow_1++;

					/**
					 * [tOracleRow_1 main ] stop
					 */
					/**
					 * [tOracleRow_1 end ] start
					 */

					currentComponent = "tOracleRow_1";

					stmt_tOracleRow_1.close();

					ok_Hash.put("tOracleRow_1", true);
					end_Hash.put("tOracleRow_1", System.currentTimeMillis());

					/**
					 * [tOracleRow_1 end ] stop
					 */

					/**
					 * [tForeach_1 end ] start
					 */

					currentComponent = "tForeach_1";

				}

				ok_Hash.put("tForeach_1", true);
				end_Hash.put("tForeach_1", System.currentTimeMillis());

				/**
				 * [tForeach_1 end ] stop
				 */

			}// end the resume

		} catch (java.lang.Exception e) {

			throw new TalendException(e, currentComponent, globalMap);

		} catch (java.lang.Error error) {

			throw error;

		}

		globalMap.put("tForeach_1_SUBPROCESS_STATE", 1);
	}

	public void tJava_2Process(final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tJava_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";
		int iterateLoop = 0;
		String currentComponent = "";

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				/**
				 * [tJava_2 begin ] start
				 */

				ok_Hash.put("tJava_2", false);
				start_Hash.put("tJava_2", System.currentTimeMillis());
				currentComponent = "tJava_2";

				int tos_count_tJava_2 = 0;

				System.out.println("ERROR@Truncate!");
				StatusListener.error("Truncate Failed!",
						((String) globalMap.get("tOracleRow_1_ERROR_MESSAGE")),
						"");

				/**
				 * [tJava_2 begin ] stop
				 */
				/**
				 * [tJava_2 main ] start
				 */

				currentComponent = "tJava_2";

				tos_count_tJava_2++;

				/**
				 * [tJava_2 main ] stop
				 */
				/**
				 * [tJava_2 end ] start
				 */

				currentComponent = "tJava_2";

				ok_Hash.put("tJava_2", true);
				end_Hash.put("tJava_2", System.currentTimeMillis());

				/**
				 * [tJava_2 end ] stop
				 */

			}// end the resume

		} catch (java.lang.Exception e) {

			throw new TalendException(e, currentComponent, globalMap);

		} catch (java.lang.Error error) {

			throw error;

		}

		globalMap.put("tJava_2_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	private java.util.Properties context_param = new java.util.Properties();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final IDRT_Truncate_Tables IDRT_Truncate_TablesClass = new IDRT_Truncate_Tables();

		int exitCode = IDRT_Truncate_TablesClass.runJobInTOS(args);
		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		try {
			// call job/subjob with an existing context, like:
			// --context=production. if without this parameter, there will use
			// the default context instead.
			java.io.InputStream inContext = IDRT_Truncate_Tables.class
					.getClassLoader().getResourceAsStream(
							"tos/idrt_truncate_tables_0_1/contexts/"
									+ contextStr + ".properties");
			if (isDefaultContext && inContext == null) {

			} else {
				if (inContext != null) {
					// defaultProps is in order to keep the original context
					// value
					defaultProps.load(inContext);
					inContext.close();
					context = new ContextProperties(defaultProps);
				} else {
					// print info and job continue to run, for case:
					// context_param is not empty.
					System.err.println("Could not find the context "
							+ contextStr);
				}
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
			}
			context.DBInstance = (String) context.getProperty("DBInstance");
			context.DBHost = (String) context.getProperty("DBHost");
			context.DBPort = (String) context.getProperty("DBPort");
			context.project = (String) context.getProperty("project");
			context.DBPassword = (java.lang.String) context
					.getProperty("DBPassword");
			context.DBSchema = (String) context.getProperty("DBSchema");
			context.DBUsername = (String) context.getProperty("DBUsername");
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
			if (parentContextMap.containsKey("DBInstance")) {
				context.DBInstance = (String) parentContextMap
						.get("DBInstance");
			}
			if (parentContextMap.containsKey("DBHost")) {
				context.DBHost = (String) parentContextMap.get("DBHost");
			}
			if (parentContextMap.containsKey("DBPort")) {
				context.DBPort = (String) parentContextMap.get("DBPort");
			}
			if (parentContextMap.containsKey("project")) {
				context.project = (String) parentContextMap.get("project");
			}
			if (parentContextMap.containsKey("DBPassword")) {
				context.DBPassword = (java.lang.String) parentContextMap
						.get("DBPassword");
			}
			if (parentContextMap.containsKey("DBSchema")) {
				context.DBSchema = (String) parentContextMap.get("DBSchema");
			}
			if (parentContextMap.containsKey("DBUsername")) {
				context.DBUsername = (String) parentContextMap
						.get("DBUsername");
			}
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil
				.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName,
				jobName, contextStr, jobVersion);

		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName,
				parent_part_launcher, Thread.currentThread().getId() + "", "",
				"", "", "", resumeUtil.convertToJsonText(context));

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tLibraryLoad_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tLibraryLoad_1) {

			e_tLibraryLoad_1.printStackTrace();
			globalMap.put("tLibraryLoad_1_SUBPROCESS_STATE", -1);

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		if (false) {
			System.out
					.println((endUsedMemory - startUsedMemory)
							+ " bytes memory increase when running : IDRT_Truncate_Tables");
		}

		int returnCode = 0;
		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher,
				Thread.currentThread().getId() + "", "", "" + returnCode, "",
				"", "");

		return returnCode;

	}

	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();
		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index),
							keyValue.substring(index + 1));
				}
			}
		}

	}

	private final String[][] escapeChars = { { "\\n", "\n" }, { "\\'", "\'" },
			{ "\\r", "\r" }, { "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" },
			{ "\\\\", "\\" } };

	private String replaceEscapeChars(String keyValue) {
		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}
		for (String[] strArray : escapeChars) {
			keyValue = keyValue.replace(strArray[0], strArray[1]);
		}
		return keyValue;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 36271 characters generated by Talend Open Studio for Data Integration on the
 * 16. Oktober 2013 10:06:43 MESZ
 ************************************************************************************************/
