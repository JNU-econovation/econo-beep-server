package com.econo.econobeepserver.domain.Equipment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEquipment is a Querydsl query type for Equipment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEquipment extends EntityPathBase<Equipment> {

    private static final long serialVersionUID = -384782024L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEquipment equipment = new QEquipment("equipment");

    public final QEquipmentImage equipmentImage;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<com.econo.econobeepserver.domain.EquipmentRental.EquipmentRental, com.econo.econobeepserver.domain.EquipmentRental.QEquipmentRental> rentalHistories = this.<com.econo.econobeepserver.domain.EquipmentRental.EquipmentRental, com.econo.econobeepserver.domain.EquipmentRental.QEquipmentRental>createList("rentalHistories", com.econo.econobeepserver.domain.EquipmentRental.EquipmentRental.class, com.econo.econobeepserver.domain.EquipmentRental.QEquipmentRental.class, PathInits.DIRECT2);

    public final NumberPath<Integer> rentCount = createNumber("rentCount", Integer.class);

    public final EnumPath<com.econo.econobeepserver.domain.RentState> rentState = createEnum("rentState", com.econo.econobeepserver.domain.RentState.class);

    public final EnumPath<com.econo.econobeepserver.domain.RenteeType> type = createEnum("type", com.econo.econobeepserver.domain.RenteeType.class);

    public QEquipment(String variable) {
        this(Equipment.class, forVariable(variable), INITS);
    }

    public QEquipment(Path<? extends Equipment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEquipment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEquipment(PathMetadata metadata, PathInits inits) {
        this(Equipment.class, metadata, inits);
    }

    public QEquipment(Class<? extends Equipment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.equipmentImage = inits.isInitialized("equipmentImage") ? new QEquipmentImage(forProperty("equipmentImage"), inits.get("equipmentImage")) : null;
    }

}

