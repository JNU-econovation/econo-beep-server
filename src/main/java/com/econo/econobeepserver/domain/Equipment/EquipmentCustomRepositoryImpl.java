package com.econo.econobeepserver.domain.Equipment;

import com.econo.econobeepserver.domain.RenteeType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.econo.econobeepserver.domain.Equipment.QEquipment.equipment;

@RequiredArgsConstructor
public class EquipmentCustomRepositoryImpl implements EquipmentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    private BooleanExpression ltEquipmentId(Long equipmentId) {
        if(equipmentId == null) {
            return null;
        }

        return equipment.id.lt(equipmentId);
    }

    @Override
    public List<Equipment> getRecentEquipmentWithPaging(int pageSize, Long lastId) {
        return jpaQueryFactory
                .select(equipment)
                .from(equipment)
                .where(ltEquipmentId(lastId))
                .orderBy(equipment.id.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Equipment> getEquipmentByTypeWithPaging(RenteeType renteeType, int pageSize, Long lastId) {
        return jpaQueryFactory
                .select(equipment)
                .from(equipment)
                .where(
                        ltEquipmentId(lastId),
                        equipment.type.eq(renteeType)
                )
                .orderBy(equipment.id.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Equipment> searchEquipmentByKeyword(String keyword) {
        return jpaQueryFactory
                .select(equipment)
                .from(equipment)
                .where(equipment.name.contains(keyword))
                .fetch();
    }
}
