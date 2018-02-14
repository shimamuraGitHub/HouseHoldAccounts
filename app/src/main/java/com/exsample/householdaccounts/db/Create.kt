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
            CODE VARCHAR(3) PRIMARY KEY,
            NAME VARCHAR(10) NOT NULL UNIQUE,
            IS_EXPENDITURE INTEGER NOT NULL
        )
    """.trimIndent()

val insertTypes = listOf(
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE ) VALUES ( '001', '外食費', 1)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE ) VALUES ( '002', '消耗品費', 1)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE ) VALUES ( '003', '飲料費', 1)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE ) VALUES ( '004', '娯楽費', 1)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE ) VALUES ( '005', '交通費', 1)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE ) VALUES ( '006', '水道光熱費', 1)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE ) VALUES ( '007', '通信費', 1)",
        "INSERT INTO RECORD_TYPE ( CODE, NAME, IS_EXPENDITURE ) VALUES ( '008', '給料', 0)"
    )

val dropAllTable = listOf(
        "DROP TABLE RECORD",
        "DROP TABLE RECORD_TYPE"
)