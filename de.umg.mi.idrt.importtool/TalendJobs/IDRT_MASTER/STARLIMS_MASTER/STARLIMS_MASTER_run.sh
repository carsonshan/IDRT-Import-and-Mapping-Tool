#!/bin/sh
cd `dirname $0`
ROOT_PATH=`pwd`
java -Xms256M -Xmx1024M -cp .:$ROOT_PATH:$ROOT_PATH/../lib/routines.jar:$ROOT_PATH/../lib/log4j-1.2.16.jar:$ROOT_PATH/../lib/geronimo-stax-api_1.0_spec-1.0.1.jar:$ROOT_PATH/../lib/log4j-1.2.15.jar:$ROOT_PATH/../lib/commons-collections-3.2.jar:$ROOT_PATH/../lib/log4j-1.2.15.jar:$ROOT_PATH/../lib/dom4j-1.6.1.jar:$ROOT_PATH/../lib/poi-ooxml-schemas-3.11-20141221.jar:$ROOT_PATH/../lib/postgresql-9.3-1102.jdbc4.jar:$ROOT_PATH/../lib/ojdbc14.jar:$ROOT_PATH/../lib/OpenCSV.jar:$ROOT_PATH/../lib/StatusListener29.jar:$ROOT_PATH/../lib/advancedPersistentLookupLib-1.0.jar:$ROOT_PATH/../lib/jboss-serialization.jar:$ROOT_PATH/../lib/xmlbeans-2.6.0.jar:$ROOT_PATH/../lib/postgresql-8.4-703.jdbc4.jar:$ROOT_PATH/../lib/poi-ooxml-3.11-20141221_modified_talend.jar:$ROOT_PATH/../lib/talendcsv.jar:$ROOT_PATH/../lib/trove.jar:$ROOT_PATH/../lib/poi-3.11-20141221_modified_talend.jar:$ROOT_PATH/../lib/talend_file_enhanced_20070724.jar:$ROOT_PATH/../lib/jakarta-oro-2.0.8.jar:$ROOT_PATH/../lib/postgresql-9.2-1003.jdbc3.jar:$ROOT_PATH/../lib/poi-scratchpad-3.11-20141221.jar:$ROOT_PATH/../lib/ojdbc6.jar:$ROOT_PATH/starlims_master_1_0.jar:$ROOT_PATH/stg_to_data_1_0.jar:$ROOT_PATH/idrt_to_db_schema_1_0.jar:$ROOT_PATH/stg_parallel_0_1.jar:$ROOT_PATH/idrt_jdbc_upload_1_0.jar:$ROOT_PATH/idrt_truncate_tables_0_1.jar:$ROOT_PATH/stg_metadata_to_ont_data_1_0.jar:$ROOT_PATH/stg_to_ont_1_0.jar:$ROOT_PATH/stg_locations_to_ont_data_1_0.jar: i2b2transmart.starlims_master_1_0.STARLIMS_MASTER --context=Default "$@" 