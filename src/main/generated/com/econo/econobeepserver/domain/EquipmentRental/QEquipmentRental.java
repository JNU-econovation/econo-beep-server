package com.econo.econobeepserver.domain.EquipmentRental;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEquipmentRental is a Querydsl query type for EquipmentRental
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEquipmentRental extends EntityPathBase<EquipmentRental> {

    private static final long serialVersionUID = -132307008L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEquipmentRental equipmentRental = new QEquipmentRental("equipmentRental");

    public final com.econo.econobeepserver.domain.Equipment.QEquipment equipment;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> rentalDateTime = createDateTime("rentalDateTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> returnDateTime = createDateTime("returnDateTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QEquipmentRental(String variable) {
        this(EquipmentRental.class, forVariable(variable), INITS);
    }

    public QEquipmentRental(Path<? extends EquipmentRental> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEquipmentRental(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEquipmentRental(PathMetadata metadata, PathInits inits) {
        this(EquipmentRental.class, metadata, inits);
    }

    public QEquipmentRental(Class<? extends EquipmentRental> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.equipment = inits.isInitialized("equipment") ? new com.econo.econobeepserver.domain.Equipment.QEquipment(forProperty("equipment"), inits.get("equipment")) : null;
    }

}

