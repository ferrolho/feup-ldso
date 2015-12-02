package models

import java.util.UUID

/**
 * The transport object.
 *
 * @param id The unique ID of the row
 * @param idSourceUser The foreign key of the source user.
 * @param idDestinyUser The foreign key of the destiny user.
 * @param idSCStock The foreign key of the Sorting Center Stock to transport.
 * @param active If the transport is occurring.
 * @param idTransporter The id of the user who will do the transport.
 */
case class Transport(
                               id: UUID,
                               idSourceUser: UUID,
                               idDestinyUser: UUID,
                               idSCStock: UUID,
                               active: Boolean,
                               idTransporter: UUID
                               )
