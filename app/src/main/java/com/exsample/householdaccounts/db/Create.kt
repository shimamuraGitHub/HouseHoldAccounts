package com.exsample.householdaccounts.db

/**
 * Created by ryosuke on 2018/02/06.
 */
val createRecord = """
        CREATE TABLE IF NOT EXISTS RECORD(
            ID VARCHAR(36) PRIMARY KEY,
            DATE DATE NOT NULL,
            TYPE_CODE VARCHAR(3) NOT NULL,
            MONEY INTEGER NOT NULL
        )
    """.trimIndent()

val createType = """
        CREATE TABLE IF NOT EXISTS RECORD_TYPE(
            CODE VARCHAR(3) NOT NULL,
            NAME VARCHAR(10) NOT NULL,
            IS_EXPENDITURE INTEGER NOT NULL,
            ENABLED INTEGER NOT NULL,
            AT_STARTED TIMESTAMP NOT NULL,
            AT_ENDED TIMESTAMP,
            PRIMARY KEY(
                CODE, NAME, ENABLED, AT_STARTED
            )
        )
    """.trimIndent()

val insertTypes = listOf(
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE, ENABLED, AT_STARTED, AT_ENDED ) VALUES ( '001', '外食費', 1, 1, '2018-02-20 00:00:00.0', NULL)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE, ENABLED, AT_STARTED, AT_ENDED ) VALUES ( '002', '消耗品費', 1, 1, '2018-02-20 00:00:00.0', NULL)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE, ENABLED, AT_STARTED, AT_ENDED ) VALUES ( '003', '飲料費', 1, 1, '2018-02-20 00:00:00.0', NULL)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE, ENABLED, AT_STARTED, AT_ENDED ) VALUES ( '004', '娯楽費', 1, 1, '2018-02-20 00:00:00.0', NULL)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE, ENABLED, AT_STARTED, AT_ENDED ) VALUES ( '005', '交通費', 1, 1, '2018-02-20 00:00:00.0', NULL)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE, ENABLED, AT_STARTED, AT_ENDED ) VALUES ( '006', '水道光熱費', 1, 1, '2018-02-20 00:00:00.0', NULL)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE, ENABLED, AT_STARTED, AT_ENDED ) VALUES ( '007', '通信費', 1, 1, '2018-02-20 00:00:00.0', NULL)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE, ENABLED, AT_STARTED, AT_ENDED ) VALUES ( '008', '交際費', 1, 1, '2018-02-20 00:00:00.0', NULL)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE, ENABLED, AT_STARTED, AT_ENDED ) VALUES ( '009', '給料', 0, 1, '2018-02-20 00:00:00.0', NULL)"
    )

val dropAllTable = listOf(
        "DROP TABLE RECORD",
        "DROP TABLE RECORD_TYPE"
)