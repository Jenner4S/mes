package com.qcadoo.mes.core.data.search.restrictions;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import junit.framework.Assert;

import org.hibernate.Criteria;
import org.hibernate.criterion.NotNullExpression;
import org.hibernate.criterion.NullExpression;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.impl.CriteriaImpl;
import org.junit.Before;
import org.junit.Test;

import com.qcadoo.mes.core.data.internal.DataAccessTest;
import com.qcadoo.mes.core.data.internal.SimpleDatabaseObject;
import com.qcadoo.mes.core.data.search.HibernateRestriction;
import com.qcadoo.mes.core.data.search.Restriction;
import com.qcadoo.mes.core.data.search.Restrictions;

public final class SimpleRestricitonTest extends DataAccessTest {

    private SimpleDatabaseObject simpleDatabaseObject = null;

    private Criteria criteria = null;

    @Before
    public void init() {
        simpleDatabaseObject = new SimpleDatabaseObject(1L);
        simpleDatabaseObject.setName("Mr T");
        simpleDatabaseObject.setAge(66);

        criteria = new CriteriaImpl(null, null);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateCriteriaWithEqRestriction() {
        // given
        Restriction restriction = Restrictions.eq(fieldDefinitionName, simpleDatabaseObject.getName());

        // when
        criteria = ((HibernateRestriction) restriction).addToHibernateCriteria(criteria);

        // then
        for (Iterator<CriteriaImpl.CriterionEntry> criterionIterator = ((CriteriaImpl) criteria).iterateExpressionEntries(); criterionIterator
                .hasNext();) {
            CriteriaImpl.CriterionEntry entry = criterionIterator.next();
            SimpleExpression simpleExpression = (SimpleExpression) entry.getCriterion();
            assertEquals(simpleExpression.toString(), "name=" + simpleDatabaseObject.getName());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateCriteriaWithGeRestriction() {
        // given
        Restriction restriction = Restrictions.ge(fieldDefinitionName, simpleDatabaseObject.getName());

        // when
        criteria = ((HibernateRestriction) restriction).addToHibernateCriteria(criteria);

        // then
        for (Iterator<CriteriaImpl.CriterionEntry> criterionIterator = ((CriteriaImpl) criteria).iterateExpressionEntries(); criterionIterator
                .hasNext();) {
            CriteriaImpl.CriterionEntry entry = criterionIterator.next();
            SimpleExpression simpleExpression = (SimpleExpression) entry.getCriterion();
            assertEquals(simpleExpression.toString(), "name>=" + simpleDatabaseObject.getName());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateCriteriaWithGtRestriction() {
        // given
        Restriction restriction = Restrictions.gt(fieldDefinitionName, simpleDatabaseObject.getName());

        // when
        criteria = ((HibernateRestriction) restriction).addToHibernateCriteria(criteria);

        // then
        for (Iterator<CriteriaImpl.CriterionEntry> criterionIterator = ((CriteriaImpl) criteria).iterateExpressionEntries(); criterionIterator
                .hasNext();) {
            CriteriaImpl.CriterionEntry entry = criterionIterator.next();
            SimpleExpression simpleExpression = (SimpleExpression) entry.getCriterion();
            assertEquals(simpleExpression.toString(), "name>" + simpleDatabaseObject.getName());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateCriteriaWithLeRestriction() {
        // given
        Restriction restriction = Restrictions.le(fieldDefinitionName, simpleDatabaseObject.getName());

        // when
        criteria = ((HibernateRestriction) restriction).addToHibernateCriteria(criteria);

        // then
        for (Iterator<CriteriaImpl.CriterionEntry> criterionIterator = ((CriteriaImpl) criteria).iterateExpressionEntries(); criterionIterator
                .hasNext();) {
            CriteriaImpl.CriterionEntry entry = criterionIterator.next();
            SimpleExpression simpleExpression = (SimpleExpression) entry.getCriterion();
            assertEquals(simpleExpression.toString(), "name<=" + simpleDatabaseObject.getName());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateCriteriaWithLtRestriction() {
        // given
        Restriction restriction = Restrictions.lt(fieldDefinitionName, simpleDatabaseObject.getName());

        // when
        criteria = ((HibernateRestriction) restriction).addToHibernateCriteria(criteria);

        // then
        for (Iterator<CriteriaImpl.CriterionEntry> criterionIterator = ((CriteriaImpl) criteria).iterateExpressionEntries(); criterionIterator
                .hasNext();) {
            CriteriaImpl.CriterionEntry entry = criterionIterator.next();
            SimpleExpression simpleExpression = (SimpleExpression) entry.getCriterion();
            assertEquals(simpleExpression.toString(), "name<" + simpleDatabaseObject.getName());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateCriteriaWithIsNullRestriction() {
        // given
        Restriction restriction = Restrictions.isNull(fieldDefinitionName);

        // when
        criteria = ((HibernateRestriction) restriction).addToHibernateCriteria(criteria);

        // then
        for (Iterator<CriteriaImpl.CriterionEntry> criterionIterator = ((CriteriaImpl) criteria).iterateExpressionEntries(); criterionIterator
                .hasNext();) {
            CriteriaImpl.CriterionEntry entry = criterionIterator.next();
            NullExpression nullExpression = (NullExpression) entry.getCriterion();
            assertEquals(nullExpression.toString(), "name is null");
        }

    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateCriteriaWithIsNotNullRestriction() {
        // given
        Restriction restriction = Restrictions.isNotNull(fieldDefinitionName);

        // when
        criteria = ((HibernateRestriction) restriction).addToHibernateCriteria(criteria);

        // then
        for (Iterator<CriteriaImpl.CriterionEntry> criterionIterator = ((CriteriaImpl) criteria).iterateExpressionEntries(); criterionIterator
                .hasNext();) {
            CriteriaImpl.CriterionEntry entry = criterionIterator.next();
            NotNullExpression notNullExpression = (NotNullExpression) entry.getCriterion();
            assertEquals(notNullExpression.toString(), "name is not null");
        }

    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateCriteriaWithStringLikeRestriction() {
        // given
        Restriction restriction = Restrictions.eq(fieldDefinitionName, "%Mr_?" + "*");

        // when
        criteria = ((HibernateRestriction) restriction).addToHibernateCriteria(criteria);

        // then
        for (Iterator<CriteriaImpl.CriterionEntry> criterionIterator = ((CriteriaImpl) criteria).iterateExpressionEntries(); criterionIterator
                .hasNext();) {
            CriteriaImpl.CriterionEntry entry = criterionIterator.next();
            SimpleExpression simpleExpression = (SimpleExpression) entry.getCriterion();
            assertEquals(simpleExpression.toString(), "name like " + "%Mr__" + "%");
        }
    }

    @Test
    public void shouldReturnNullIfOrderValidationResultsNotEmpty() throws Exception {
        // given

        // when
        Restriction restriction = Restrictions.eq(fieldDefinitionAge, "Mr");

        // then
        Assert.assertNull(restriction);
    }
}
