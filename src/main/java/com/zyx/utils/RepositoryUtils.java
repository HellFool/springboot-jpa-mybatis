package com.zyx.utils;

import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class RepositoryUtils {
  private static final int BETWEEN_LENGTH = 2;

  private RepositoryUtils() {
  }

  public static void applyBetween(List<Predicate> predicates, Path path, CriteriaBuilder cb, Date[] dates) {
    if (null != dates && dates.length == 2) {
      if (null != dates[0]) {
        predicates.add(cb.greaterThanOrEqualTo(path, dates[0]));
      }

      if (null != dates[1]) {
        predicates.add(cb.lessThanOrEqualTo(path, dates[1]));
      }
    }

  }

  public static void applyBetween(List<Predicate> predicates, Path path, CriteriaBuilder cb, Double[] values) {
    if (null != values && values.length == 2) {
      if (null != values[0]) {
        predicates.add(cb.greaterThanOrEqualTo(path, values[0]));
      }

      if (null != values[1]) {
        predicates.add(cb.lessThanOrEqualTo(path, values[1]));
      }
    }

  }

  public static void applyBetween(List<Predicate> predicates, Path path, CriteriaBuilder cb, Long[] values) {
    if (null != values && values.length == 2) {
      if (null != values[0]) {
        predicates.add(cb.greaterThanOrEqualTo(path, values[0]));
      }

      if (null != values[1]) {
        predicates.add(cb.lessThanOrEqualTo(path, values[1]));
      }
    }

  }

  public static final Predicate buildPredicate(CriteriaBuilder cb, List<Predicate> predicates) {
    if (null != predicates && !predicates.isEmpty()) {
      Predicate predicate = (Predicate)predicates.get(0);

      for(int i = 1; i < predicates.size(); ++i) {
        predicate = cb.and(predicate, (Expression)predicates.get(i));
      }

      return predicate;
    } else {
      return null;
    }
  }
}

