package models

import java.util.UUID

/**
 * The sorting center stock object.
 *
 * @param id The unique ID of the row
 * @param idSupply The unique ID of the supply.
 * @param userID The foreign key of the user who owns the sorting center.
 * @param supplyUserID The foreign key of the user who owns the supply.
 * @param resource The name of the resource in the sorting center.
 * @param resourceCategoryID The id of the resource category which the stock belongs.
 * @param amount The amount of the resource in the sorting center.
 * @param amountLabelID The id of the amount label which the stock belongs.
 */
case class SortingCenterStock(
                               id: UUID,
                               idSupply: UUID,
                               userID: UUID,
                               supplyUserID: UUID,
                               resource: String,
                               resourceCategoryID: Long,
                               amount: Int,
                               amountLabelID: Long
                               )
