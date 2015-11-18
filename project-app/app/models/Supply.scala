package models

import java.util.UUID

/**
 * The supply object.
 *
 * @param id The unique ID of the supply.
 * @param userID The foreign key of the user who created the supply.
 * @param resource The name of the resource in the supply.
 * @param amount The amount of the resource in the supply.
 */
case class Supply(
                   id: UUID,
                   userID: UUID,
                   resource: String,
                   amount: Int)
