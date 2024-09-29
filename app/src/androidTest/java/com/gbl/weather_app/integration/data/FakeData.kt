package com.gbl.weather_app.integration.data

import com.gbl.weather_app.domain.model.Location

val fakeLocations = List<Location>(5){ i->
    object : Location {
        override val id: Int = i
        override val name: String = if (i==3) TARGET_LOCATION_NAME else "Some another location N$i"
        override val latitude: Double = 0.0
        override val longitude: Double = 0.0
    }
}

const val TARGET_LOCATION_NAME = "Target location"