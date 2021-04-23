package com.zyx.repository;

import com.zyx.entity.user.User;
import com.zyx.utils.RepositoryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.util.StringUtils;


import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor {

    User findByUserId(String userId);

    @Query("select m from User as m where m.id=1")
    User getUser();


    Page findAll(Pageable pageable);


    default Page findAll(String userId,Pageable pageable){
        return this.findAll((root,query,cb) -> {
            List<Predicate> predicates=new ArrayList<>();
            if(!StringUtils.isEmpty(userId)){
                predicates.add(cb.equal(root.get("userId"),userId));
            }
            return RepositoryUtils.buildPredicate(cb,predicates);
        },pageable);

    }
}
