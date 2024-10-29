package com.conference.room.booking.api.models

import org.springframework.data.repository.CrudRepository

interface BookingRepository : CrudRepository<Booking, Int>
