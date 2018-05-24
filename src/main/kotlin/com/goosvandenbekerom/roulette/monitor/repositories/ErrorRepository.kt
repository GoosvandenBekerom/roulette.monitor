package com.goosvandenbekerom.roulette.monitor.repositories

import com.goosvandenbekerom.roulette.monitor.domain.ErrorEntry
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ErrorRepository : PagingAndSortingRepository<ErrorEntry, Long>