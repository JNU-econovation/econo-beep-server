package com.econo.econobeepserver.domain.Equipment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEquipmentImage is a Querydsl query type for EquipmentImage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEquipmentImage extends EntityPathBase<EquipmentImage> {

    private static final long serialVersionUID = -1367536893L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEquipmentImage equipmentImage = new QEquipmentImage("equipmentImage");

    public final QEquipment equipment;

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QEquipmentImage(String variable) {
        this(EquipmentImage.class, forVariable(variable), INITS);
    }

    public QEquipmentImage(Path<? extends EquipmentImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEquipmentImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEquipmentImage(PathMetadata metadata, PathInits inits) {
        this(EquipmentImage.class, metadata, inits);
    }

    public QEquipmentImage(Class<? extends EquipmentImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.equipment = inits.isInitialized("equipment") ? new QEquipment(forProperty("equipment"), inits.get("equipment")) : null;
    }

}

