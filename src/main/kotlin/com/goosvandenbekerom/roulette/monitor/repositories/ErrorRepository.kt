package com.goosvandenbekerom.roulette.monitor.repositories

import com.goosvandenbekerom.roulette.monitor.domain.ErrorEntry
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ErrorRepository : CrudRepository<ErrorEntry, Long>