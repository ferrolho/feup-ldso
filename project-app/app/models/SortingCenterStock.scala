package models

import java.util.UUID

/**
 * The sorting center stock object.
 *
 * @param id The unique ID of the row
 * @param idSupply The unique ID of the supply.
 * @param userID The foreign key of the user who owns the sorting center.
 * @param resource The name of the resource in the sorting center.
 * @param amount The amount of the resource in the sorting center.
 */
case class SortingCenterStock(
                               id: UUID,
                               idSupply: UUID,
                               userID: UUID,
                               resource: String,
                               amount: Int
                               )
