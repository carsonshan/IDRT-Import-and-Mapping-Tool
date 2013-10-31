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

package tos.server_freelocks_0_1;

import routines.IIT;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.Numeric;
import routines.ExportDB;
import routines.enc_num_routine;
import routines.Mathematical;
import routines.Relational;
import routines.TalendDate;
import routines.IDRTHelper;
import routines.PIDGen;
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

//the import part of tJava_1
//import java.util.List;

//the import part of tJavaRow_1
//import java.util.List;

@SuppressWarnings("unused")
/**
 * Job: SERVER_FreeLocks Purpose: <br>
 * Description:  <br>
 * @author test@talend.com
 * @version 5.3.0.r101800
 * @status 
 */
public class SERVER_FreeLocks implements TalendJob {

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
	private final String jobName = "SERVER_FreeLocks";
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
					SERVER_FreeLocks.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass()
							.getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(SERVER_FreeLocks.this, new Object[] { e,
									currentComponent, globalMap });
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
			} else {

				status = "failure";

			}
		}
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

	public void tOracleInput_1_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tOracleInput_1", System.currentTimeMillis());

		status = "failure";

		tOracleInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaRow_1_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tJavaRow_1", System.currentTimeMillis());

		status = "failure";

		tOracleInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tOracleRow_1_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tOracleRow_1", System.currentTimeMillis());

		status = "failure";

		tOracleInput_1_onSubJobError(exception, errorComponent, globalMap);
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

	public void tOracleInput_1_onSubJobError(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

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

				System.out.println("Trying to free locks.");

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

			throw new java.lang.Error(error);

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

					conn_tOracleConnection_1.setAutoCommit(false);
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

			tOracleInput_1Process(globalMap);

		} catch (java.lang.Exception e) {

			throw new TalendException(e, currentComponent, globalMap);

		} catch (java.lang.Error error) {

			throw new java.lang.Error(error);

		}

		globalMap.put("tOracleConnection_1_SUBPROCESS_STATE", 1);
	}

	public static class row2Struct implements
			routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_TOS_SERVER_FreeLocks = new byte[0];
		static byte[] commonByteArray_TOS_SERVER_FreeLocks = new byte[0];

		public BigDecimal SID;

		public BigDecimal getSID() {
			return this.SID;
		}

		public BigDecimal SERIAL_;

		public BigDecimal getSERIAL_() {
			return this.SERIAL_;
		}

		public String SPID;

		public String getSPID() {
			return this.SPID;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TOS_SERVER_FreeLocks.length) {
					if (length < 1024
							&& commonByteArray_TOS_SERVER_FreeLocks.length == 0) {
						commonByteArray_TOS_SERVER_FreeLocks = new byte[1024];
					} else {
						commonByteArray_TOS_SERVER_FreeLocks = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TOS_SERVER_FreeLocks, 0, length);
				strReturn = new String(commonByteArray_TOS_SERVER_FreeLocks, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TOS_SERVER_FreeLocks) {

				try {

					int length = 0;

					this.SID = (BigDecimal) dis.readObject();

					this.SERIAL_ = (BigDecimal) dis.readObject();

					this.SPID = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// BigDecimal

				dos.writeObject(this.SID);

				// BigDecimal

				dos.writeObject(this.SERIAL_);

				// String

				writeString(this.SPID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("SID=" + String.valueOf(SID));
			sb.append(",SERIAL_=" + String.valueOf(SERIAL_));
			sb.append(",SPID=" + SPID);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements
			routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_TOS_SERVER_FreeLocks = new byte[0];
		static byte[] commonByteArray_TOS_SERVER_FreeLocks = new byte[0];

		public BigDecimal SID;

		public BigDecimal getSID() {
			return this.SID;
		}

		public BigDecimal SERIAL_;

		public BigDecimal getSERIAL_() {
			return this.SERIAL_;
		}

		public String SPID;

		public String getSPID() {
			return this.SPID;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TOS_SERVER_FreeLocks.length) {
					if (length < 1024
							&& commonByteArray_TOS_SERVER_FreeLocks.length == 0) {
						commonByteArray_TOS_SERVER_FreeLocks = new byte[1024];
					} else {
						commonByteArray_TOS_SERVER_FreeLocks = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TOS_SERVER_FreeLocks, 0, length);
				strReturn = new String(commonByteArray_TOS_SERVER_FreeLocks, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TOS_SERVER_FreeLocks) {

				try {

					int length = 0;

					this.SID = (BigDecimal) dis.readObject();

					this.SERIAL_ = (BigDecimal) dis.readObject();

					this.SPID = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// BigDecimal

				dos.writeObject(this.SID);

				// BigDecimal

				dos.writeObject(this.SERIAL_);

				// String

				writeString(this.SPID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("SID=" + String.valueOf(SID));
			sb.append(",SERIAL_=" + String.valueOf(SERIAL_));
			sb.append(",SPID=" + SPID);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tOracleInput_1Process(
			final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tOracleInput_1_SUBPROCESS_STATE", 0);

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

				row1Struct row1 = new row1Struct();
				row2Struct row2 = new row2Struct();

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
				 * [tJavaRow_1 begin ] start
				 */

				ok_Hash.put("tJavaRow_1", false);
				start_Hash.put("tJavaRow_1", System.currentTimeMillis());
				currentComponent = "tJavaRow_1";

				int tos_count_tJavaRow_1 = 0;

				int nb_line_tJavaRow_1 = 0;

				/**
				 * [tJavaRow_1 begin ] stop
				 */

				/**
				 * [tOracleInput_1 begin ] start
				 */

				ok_Hash.put("tOracleInput_1", false);
				start_Hash.put("tOracleInput_1", System.currentTimeMillis());
				currentComponent = "tOracleInput_1";

				int tos_count_tOracleInput_1 = 0;

				int nb_line_tOracleInput_1 = 0;
				java.sql.Connection conn_tOracleInput_1 = null;
				conn_tOracleInput_1 = (java.sql.Connection) globalMap
						.get("conn_tOracleConnection_1");
				if (null == conn_tOracleInput_1) {
					java.util.Map<String, routines.system.TalendDataSource> dataSources_tOracleInput_1 = (java.util.Map<String, routines.system.TalendDataSource>) globalMap
							.get(KEY_DB_DATASOURCES);
					conn_tOracleInput_1 = dataSources_tOracleInput_1.get("")
							.getConnection();
					// globalMap.put("conn_tOracleConnection_1",
					// conn_tOracleInput_1);
				}
				if (((oracle.jdbc.OracleConnection) conn_tOracleInput_1)
						.getSessionTimeZone() == null) {
					java.sql.Statement stmtGetTZ_tOracleInput_1 = conn_tOracleInput_1
							.createStatement();
					java.sql.ResultSet rsGetTZ_tOracleInput_1 = stmtGetTZ_tOracleInput_1
							.executeQuery("select sessiontimezone from dual");
					String sessionTimezone_tOracleInput_1 = java.util.TimeZone
							.getDefault().getID();
					while (rsGetTZ_tOracleInput_1.next()) {
						sessionTimezone_tOracleInput_1 = rsGetTZ_tOracleInput_1
								.getString(1);
					}
					((oracle.jdbc.OracleConnection) conn_tOracleInput_1)
							.setSessionTimeZone(sessionTimezone_tOracleInput_1);
				}

				java.sql.Statement stmt_tOracleInput_1 = conn_tOracleInput_1
						.createStatement();

				String dbquery_tOracleInput_1 = "select s.sid, s.serial#, p.spid  from     v$session s,     v$process p  where     s.paddr = p.addr  and     s.sid in (select SESSION_ID from v$locked_object)";

				globalMap.put("tOracleInput_1_QUERY", dbquery_tOracleInput_1);

				java.sql.ResultSet rs_tOracleInput_1 = stmt_tOracleInput_1
						.executeQuery(dbquery_tOracleInput_1);
				java.sql.ResultSetMetaData rsmd_tOracleInput_1 = rs_tOracleInput_1
						.getMetaData();
				int colQtyInRs_tOracleInput_1 = rsmd_tOracleInput_1
						.getColumnCount();

				String tmpContent_tOracleInput_1 = null;
				int column_index_tOracleInput_1 = 1;
				while (rs_tOracleInput_1.next()) {
					nb_line_tOracleInput_1++;

					column_index_tOracleInput_1 = 1;

					if (colQtyInRs_tOracleInput_1 < column_index_tOracleInput_1) {
						row1.SID = null;
					} else {

						if (rs_tOracleInput_1
								.getObject(column_index_tOracleInput_1) != null) {
							row1.SID = rs_tOracleInput_1
									.getBigDecimal(column_index_tOracleInput_1);
						} else {

							row1.SID = null;
						}

					}
					column_index_tOracleInput_1 = 2;

					if (colQtyInRs_tOracleInput_1 < column_index_tOracleInput_1) {
						row1.SERIAL_ = null;
					} else {

						if (rs_tOracleInput_1
								.getObject(column_index_tOracleInput_1) != null) {
							row1.SERIAL_ = rs_tOracleInput_1
									.getBigDecimal(column_index_tOracleInput_1);
						} else {

							row1.SERIAL_ = null;
						}

					}
					column_index_tOracleInput_1 = 3;

					if (colQtyInRs_tOracleInput_1 < column_index_tOracleInput_1) {
						row1.SPID = null;
					} else {

						tmpContent_tOracleInput_1 = rs_tOracleInput_1
								.getString(column_index_tOracleInput_1);
						if (tmpContent_tOracleInput_1 != null) {
							row1.SPID = tmpContent_tOracleInput_1;
						} else {
							row1.SPID = null;
						}

					}

					/**
					 * [tOracleInput_1 begin ] stop
					 */
					/**
					 * [tOracleInput_1 main ] start
					 */

					currentComponent = "tOracleInput_1";

					tos_count_tOracleInput_1++;

					/**
					 * [tOracleInput_1 main ] stop
					 */

					/**
					 * [tJavaRow_1 main ] start
					 */

					currentComponent = "tJavaRow_1";

					// Code generated according to input schema and output
					// schema
					row2.SID = row1.SID;
					row2.SERIAL_ = row1.SERIAL_;
					row2.SPID = row1.SPID;
					System.out.println("LOCKED: " + row1.SPID + " (" + row1.SID
							+ "," + row1.SERIAL_ + ")");
					nb_line_tJavaRow_1++;

					tos_count_tJavaRow_1++;

					/**
					 * [tJavaRow_1 main ] stop
					 */

					/**
					 * [tOracleRow_1 main ] start
					 */

					currentComponent = "tOracleRow_1";

					query_tOracleRow_1 = "ALTER SYSTEM KILL SESSION '"
							+ row2.SID + "," + row2.SERIAL_ + "'";
					whetherReject_tOracleRow_1 = false;

					globalMap.put("tOracleRow_1_QUERY", query_tOracleRow_1);
					try {
						stmt_tOracleRow_1.execute(query_tOracleRow_1);

					} catch (java.lang.Exception e) {
						whetherReject_tOracleRow_1 = true;
						throw (e);
					}

					tos_count_tOracleRow_1++;

					/**
					 * [tOracleRow_1 main ] stop
					 */

					/**
					 * [tOracleInput_1 end ] start
					 */

					currentComponent = "tOracleInput_1";

				}
				stmt_tOracleInput_1.close();

				globalMap.put("tOracleInput_1_NB_LINE", nb_line_tOracleInput_1);

				ok_Hash.put("tOracleInput_1", true);
				end_Hash.put("tOracleInput_1", System.currentTimeMillis());

				/**
				 * [tOracleInput_1 end ] stop
				 */

				/**
				 * [tJavaRow_1 end ] start
				 */

				currentComponent = "tJavaRow_1";

				globalMap.put("tJavaRow_1_NB_LINE", nb_line_tJavaRow_1);

				ok_Hash.put("tJavaRow_1", true);
				end_Hash.put("tJavaRow_1", System.currentTimeMillis());

				/**
				 * [tJavaRow_1 end ] stop
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

			}// end the resume

		} catch (java.lang.Exception e) {

			throw new TalendException(e, currentComponent, globalMap);

		} catch (java.lang.Error error) {

			throw new java.lang.Error(error);

		}

		globalMap.put("tOracleInput_1_SUBPROCESS_STATE", 1);
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
		final SERVER_FreeLocks SERVER_FreeLocksClass = new SERVER_FreeLocks();

		int exitCode = SERVER_FreeLocksClass.runJobInTOS(args);
		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public int runJobInTOS(String[] args) {

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
			java.io.InputStream inContext = SERVER_FreeLocks.class
					.getClassLoader().getResourceAsStream(
							"tos/server_freelocks_0_1/contexts/" + contextStr
									+ ".properties");
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
			tJava_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tJava_1) {

			e_tJava_1.printStackTrace();
			globalMap.put("tJava_1_SUBPROCESS_STATE", -1);

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory)
					+ " bytes memory increase when running : SERVER_FreeLocks");
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
 * 42639 characters generated by Talend Open Studio for Data Integration on the
 * October 31, 2013 11:58:42 AM CET
 ************************************************************************************************/