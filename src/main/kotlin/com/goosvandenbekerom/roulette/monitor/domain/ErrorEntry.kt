package com.goosvandenbekerom.roulette.monitor.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ErrorEntry(val message: String = "", val context: String = "", val username: String = "", @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0)