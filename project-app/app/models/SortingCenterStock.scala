package models

import java.util.UUID

/**
 * The sorting center stock object.
 *
 * @param idResource The unique ID of the supply.
 * @param userID The foreign key of the user who owns the sorting center.
 * @param idSortingCenter The foreign key of the sorting Center
 * @param resource The name of the resource in the sorting center.
 * @param amount The amount of the resource in the sorting center.
 */
case class SortingCenterStock(
                               idResource: UUID,
                               userID: UUID,
                               idSortingCenter: UUID,
                               resource: String,
                               amount: Int,
                               inSortingCenter: Boolean = false
                               )
