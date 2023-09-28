package ru.uxfeedback.demoapplication.api.logs.entities

import org.joda.time.Instant
import ru.uxfeedback.demoapplication.api.logs.entities.LogRecordType.CAMPAIGN
import ru.uxfeedback.demoapplication.api.logs.entities.LogRecordType.LOG


data class LogRecord(val date: String, val message: String, val type: LogRecordType){
    companion object{
        fun obtainCampaign(message: String): LogRecord{
            return LogRecord(Instant.now().toString(), message, CAMPAIGN)
        }
        fun obtainLog(message: String): LogRecord{
            return LogRecord(Instant.now().toString(), message, LOG)
        }
    }
}