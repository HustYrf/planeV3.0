package hust.plane.mapper.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("Name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("Name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("Name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("Name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("Name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("Name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("Name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("Name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("Name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("Name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("Name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("Name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("Name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("Name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andUsercreatorIsNull() {
            addCriterion("UserCreator is null");
            return (Criteria) this;
        }

        public Criteria andUsercreatorIsNotNull() {
            addCriterion("UserCreator is not null");
            return (Criteria) this;
        }

        public Criteria andUsercreatorEqualTo(Integer value) {
            addCriterion("UserCreator =", value, "usercreator");
            return (Criteria) this;
        }

        public Criteria andUsercreatorNotEqualTo(Integer value) {
            addCriterion("UserCreator <>", value, "usercreator");
            return (Criteria) this;
        }

        public Criteria andUsercreatorGreaterThan(Integer value) {
            addCriterion("UserCreator >", value, "usercreator");
            return (Criteria) this;
        }

        public Criteria andUsercreatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("UserCreator >=", value, "usercreator");
            return (Criteria) this;
        }

        public Criteria andUsercreatorLessThan(Integer value) {
            addCriterion("UserCreator <", value, "usercreator");
            return (Criteria) this;
        }

        public Criteria andUsercreatorLessThanOrEqualTo(Integer value) {
            addCriterion("UserCreator <=", value, "usercreator");
            return (Criteria) this;
        }

        public Criteria andUsercreatorIn(List<Integer> values) {
            addCriterion("UserCreator in", values, "usercreator");
            return (Criteria) this;
        }

        public Criteria andUsercreatorNotIn(List<Integer> values) {
            addCriterion("UserCreator not in", values, "usercreator");
            return (Criteria) this;
        }

        public Criteria andUsercreatorBetween(Integer value1, Integer value2) {
            addCriterion("UserCreator between", value1, value2, "usercreator");
            return (Criteria) this;
        }

        public Criteria andUsercreatorNotBetween(Integer value1, Integer value2) {
            addCriterion("UserCreator not between", value1, value2, "usercreator");
            return (Criteria) this;
        }

        public Criteria andUserAIsNull() {
            addCriterion("User_A is null");
            return (Criteria) this;
        }

        public Criteria andUserAIsNotNull() {
            addCriterion("User_A is not null");
            return (Criteria) this;
        }

        public Criteria andUserAEqualTo(Integer value) {
            addCriterion("User_A =", value, "userA");
            return (Criteria) this;
        }

        public Criteria andUserANotEqualTo(Integer value) {
            addCriterion("User_A <>", value, "userA");
            return (Criteria) this;
        }

        public Criteria andUserAGreaterThan(Integer value) {
            addCriterion("User_A >", value, "userA");
            return (Criteria) this;
        }

        public Criteria andUserAGreaterThanOrEqualTo(Integer value) {
            addCriterion("User_A >=", value, "userA");
            return (Criteria) this;
        }

        public Criteria andUserALessThan(Integer value) {
            addCriterion("User_A <", value, "userA");
            return (Criteria) this;
        }

        public Criteria andUserALessThanOrEqualTo(Integer value) {
            addCriterion("User_A <=", value, "userA");
            return (Criteria) this;
        }

        public Criteria andUserAIn(List<Integer> values) {
            addCriterion("User_A in", values, "userA");
            return (Criteria) this;
        }

        public Criteria andUserANotIn(List<Integer> values) {
            addCriterion("User_A not in", values, "userA");
            return (Criteria) this;
        }

        public Criteria andUserABetween(Integer value1, Integer value2) {
            addCriterion("User_A between", value1, value2, "userA");
            return (Criteria) this;
        }

        public Criteria andUserANotBetween(Integer value1, Integer value2) {
            addCriterion("User_A not between", value1, value2, "userA");
            return (Criteria) this;
        }

        public Criteria andUserZIsNull() {
            addCriterion("User_Z is null");
            return (Criteria) this;
        }

        public Criteria andUserZIsNotNull() {
            addCriterion("User_Z is not null");
            return (Criteria) this;
        }

        public Criteria andUserZEqualTo(Integer value) {
            addCriterion("User_Z =", value, "userZ");
            return (Criteria) this;
        }

        public Criteria andUserZNotEqualTo(Integer value) {
            addCriterion("User_Z <>", value, "userZ");
            return (Criteria) this;
        }

        public Criteria andUserZGreaterThan(Integer value) {
            addCriterion("User_Z >", value, "userZ");
            return (Criteria) this;
        }

        public Criteria andUserZGreaterThanOrEqualTo(Integer value) {
            addCriterion("User_Z >=", value, "userZ");
            return (Criteria) this;
        }

        public Criteria andUserZLessThan(Integer value) {
            addCriterion("User_Z <", value, "userZ");
            return (Criteria) this;
        }

        public Criteria andUserZLessThanOrEqualTo(Integer value) {
            addCriterion("User_Z <=", value, "userZ");
            return (Criteria) this;
        }

        public Criteria andUserZIn(List<Integer> values) {
            addCriterion("User_Z in", values, "userZ");
            return (Criteria) this;
        }

        public Criteria andUserZNotIn(List<Integer> values) {
            addCriterion("User_Z not in", values, "userZ");
            return (Criteria) this;
        }

        public Criteria andUserZBetween(Integer value1, Integer value2) {
            addCriterion("User_Z between", value1, value2, "userZ");
            return (Criteria) this;
        }

        public Criteria andUserZNotBetween(Integer value1, Integer value2) {
            addCriterion("User_Z not between", value1, value2, "userZ");
            return (Criteria) this;
        }

        public Criteria andFlyingpathIdIsNull() {
            addCriterion("FlyingPath_id is null");
            return (Criteria) this;
        }

        public Criteria andFlyingpathIdIsNotNull() {
            addCriterion("FlyingPath_id is not null");
            return (Criteria) this;
        }

        public Criteria andFlyingpathIdEqualTo(Integer value) {
            addCriterion("FlyingPath_id =", value, "flyingpathId");
            return (Criteria) this;
        }

        public Criteria andFlyingpathIdNotEqualTo(Integer value) {
            addCriterion("FlyingPath_id <>", value, "flyingpathId");
            return (Criteria) this;
        }

        public Criteria andFlyingpathIdGreaterThan(Integer value) {
            addCriterion("FlyingPath_id >", value, "flyingpathId");
            return (Criteria) this;
        }

        public Criteria andFlyingpathIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("FlyingPath_id >=", value, "flyingpathId");
            return (Criteria) this;
        }

        public Criteria andFlyingpathIdLessThan(Integer value) {
            addCriterion("FlyingPath_id <", value, "flyingpathId");
            return (Criteria) this;
        }

        public Criteria andFlyingpathIdLessThanOrEqualTo(Integer value) {
            addCriterion("FlyingPath_id <=", value, "flyingpathId");
            return (Criteria) this;
        }

        public Criteria andFlyingpathIdIn(List<Integer> values) {
            addCriterion("FlyingPath_id in", values, "flyingpathId");
            return (Criteria) this;
        }

        public Criteria andFlyingpathIdNotIn(List<Integer> values) {
            addCriterion("FlyingPath_id not in", values, "flyingpathId");
            return (Criteria) this;
        }

        public Criteria andFlyingpathIdBetween(Integer value1, Integer value2) {
            addCriterion("FlyingPath_id between", value1, value2, "flyingpathId");
            return (Criteria) this;
        }

        public Criteria andFlyingpathIdNotBetween(Integer value1, Integer value2) {
            addCriterion("FlyingPath_id not between", value1, value2, "flyingpathId");
            return (Criteria) this;
        }

        public Criteria andUavIdIsNull() {
            addCriterion("Uav_id is null");
            return (Criteria) this;
        }

        public Criteria andUavIdIsNotNull() {
            addCriterion("Uav_id is not null");
            return (Criteria) this;
        }

        public Criteria andUavIdEqualTo(Integer value) {
            addCriterion("Uav_id =", value, "uavId");
            return (Criteria) this;
        }

        public Criteria andUavIdNotEqualTo(Integer value) {
            addCriterion("Uav_id <>", value, "uavId");
            return (Criteria) this;
        }

        public Criteria andUavIdGreaterThan(Integer value) {
            addCriterion("Uav_id >", value, "uavId");
            return (Criteria) this;
        }

        public Criteria andUavIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Uav_id >=", value, "uavId");
            return (Criteria) this;
        }

        public Criteria andUavIdLessThan(Integer value) {
            addCriterion("Uav_id <", value, "uavId");
            return (Criteria) this;
        }

        public Criteria andUavIdLessThanOrEqualTo(Integer value) {
            addCriterion("Uav_id <=", value, "uavId");
            return (Criteria) this;
        }

        public Criteria andUavIdIn(List<Integer> values) {
            addCriterion("Uav_id in", values, "uavId");
            return (Criteria) this;
        }

        public Criteria andUavIdNotIn(List<Integer> values) {
            addCriterion("Uav_id not in", values, "uavId");
            return (Criteria) this;
        }

        public Criteria andUavIdBetween(Integer value1, Integer value2) {
            addCriterion("Uav_id between", value1, value2, "uavId");
            return (Criteria) this;
        }

        public Criteria andUavIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Uav_id not between", value1, value2, "uavId");
            return (Criteria) this;
        }

        public Criteria andCeatetimeIsNull() {
            addCriterion("CeateTime is null");
            return (Criteria) this;
        }

        public Criteria andCeatetimeIsNotNull() {
            addCriterion("CeateTime is not null");
            return (Criteria) this;
        }

        public Criteria andCeatetimeEqualTo(Date value) {
            addCriterion("CeateTime =", value, "ceatetime");
            return (Criteria) this;
        }

        public Criteria andCeatetimeNotEqualTo(Date value) {
            addCriterion("CeateTime <>", value, "ceatetime");
            return (Criteria) this;
        }

        public Criteria andCeatetimeGreaterThan(Date value) {
            addCriterion("CeateTime >", value, "ceatetime");
            return (Criteria) this;
        }

        public Criteria andCeatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CeateTime >=", value, "ceatetime");
            return (Criteria) this;
        }

        public Criteria andCeatetimeLessThan(Date value) {
            addCriterion("CeateTime <", value, "ceatetime");
            return (Criteria) this;
        }

        public Criteria andCeatetimeLessThanOrEqualTo(Date value) {
            addCriterion("CeateTime <=", value, "ceatetime");
            return (Criteria) this;
        }

        public Criteria andCeatetimeIn(List<Date> values) {
            addCriterion("CeateTime in", values, "ceatetime");
            return (Criteria) this;
        }

        public Criteria andCeatetimeNotIn(List<Date> values) {
            addCriterion("CeateTime not in", values, "ceatetime");
            return (Criteria) this;
        }

        public Criteria andCeatetimeBetween(Date value1, Date value2) {
            addCriterion("CeateTime between", value1, value2, "ceatetime");
            return (Criteria) this;
        }

        public Criteria andCeatetimeNotBetween(Date value1, Date value2) {
            addCriterion("CeateTime not between", value1, value2, "ceatetime");
            return (Criteria) this;
        }

        public Criteria andPlanstarttimeIsNull() {
            addCriterion("PlanStartTime is null");
            return (Criteria) this;
        }

        public Criteria andPlanstarttimeIsNotNull() {
            addCriterion("PlanStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andPlanstarttimeEqualTo(Date value) {
            addCriterion("PlanStartTime =", value, "planstarttime");
            return (Criteria) this;
        }

        public Criteria andPlanstarttimeNotEqualTo(Date value) {
            addCriterion("PlanStartTime <>", value, "planstarttime");
            return (Criteria) this;
        }

        public Criteria andPlanstarttimeGreaterThan(Date value) {
            addCriterion("PlanStartTime >", value, "planstarttime");
            return (Criteria) this;
        }

        public Criteria andPlanstarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PlanStartTime >=", value, "planstarttime");
            return (Criteria) this;
        }

        public Criteria andPlanstarttimeLessThan(Date value) {
            addCriterion("PlanStartTime <", value, "planstarttime");
            return (Criteria) this;
        }

        public Criteria andPlanstarttimeLessThanOrEqualTo(Date value) {
            addCriterion("PlanStartTime <=", value, "planstarttime");
            return (Criteria) this;
        }

        public Criteria andPlanstarttimeIn(List<Date> values) {
            addCriterion("PlanStartTime in", values, "planstarttime");
            return (Criteria) this;
        }

        public Criteria andPlanstarttimeNotIn(List<Date> values) {
            addCriterion("PlanStartTime not in", values, "planstarttime");
            return (Criteria) this;
        }

        public Criteria andPlanstarttimeBetween(Date value1, Date value2) {
            addCriterion("PlanStartTime between", value1, value2, "planstarttime");
            return (Criteria) this;
        }

        public Criteria andPlanstarttimeNotBetween(Date value1, Date value2) {
            addCriterion("PlanStartTime not between", value1, value2, "planstarttime");
            return (Criteria) this;
        }

        public Criteria andPlanendtimeIsNull() {
            addCriterion("PlanEndTime is null");
            return (Criteria) this;
        }

        public Criteria andPlanendtimeIsNotNull() {
            addCriterion("PlanEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andPlanendtimeEqualTo(Date value) {
            addCriterion("PlanEndTime =", value, "planendtime");
            return (Criteria) this;
        }

        public Criteria andPlanendtimeNotEqualTo(Date value) {
            addCriterion("PlanEndTime <>", value, "planendtime");
            return (Criteria) this;
        }

        public Criteria andPlanendtimeGreaterThan(Date value) {
            addCriterion("PlanEndTime >", value, "planendtime");
            return (Criteria) this;
        }

        public Criteria andPlanendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PlanEndTime >=", value, "planendtime");
            return (Criteria) this;
        }

        public Criteria andPlanendtimeLessThan(Date value) {
            addCriterion("PlanEndTime <", value, "planendtime");
            return (Criteria) this;
        }

        public Criteria andPlanendtimeLessThanOrEqualTo(Date value) {
            addCriterion("PlanEndTime <=", value, "planendtime");
            return (Criteria) this;
        }

        public Criteria andPlanendtimeIn(List<Date> values) {
            addCriterion("PlanEndTime in", values, "planendtime");
            return (Criteria) this;
        }

        public Criteria andPlanendtimeNotIn(List<Date> values) {
            addCriterion("PlanEndTime not in", values, "planendtime");
            return (Criteria) this;
        }

        public Criteria andPlanendtimeBetween(Date value1, Date value2) {
            addCriterion("PlanEndTime between", value1, value2, "planendtime");
            return (Criteria) this;
        }

        public Criteria andPlanendtimeNotBetween(Date value1, Date value2) {
            addCriterion("PlanEndTime not between", value1, value2, "planendtime");
            return (Criteria) this;
        }

        public Criteria andExecutestarttimeIsNull() {
            addCriterion("ExecuteStartTime is null");
            return (Criteria) this;
        }

        public Criteria andExecutestarttimeIsNotNull() {
            addCriterion("ExecuteStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andExecutestarttimeEqualTo(Date value) {
            addCriterion("ExecuteStartTime =", value, "executestarttime");
            return (Criteria) this;
        }

        public Criteria andExecutestarttimeNotEqualTo(Date value) {
            addCriterion("ExecuteStartTime <>", value, "executestarttime");
            return (Criteria) this;
        }

        public Criteria andExecutestarttimeGreaterThan(Date value) {
            addCriterion("ExecuteStartTime >", value, "executestarttime");
            return (Criteria) this;
        }

        public Criteria andExecutestarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ExecuteStartTime >=", value, "executestarttime");
            return (Criteria) this;
        }

        public Criteria andExecutestarttimeLessThan(Date value) {
            addCriterion("ExecuteStartTime <", value, "executestarttime");
            return (Criteria) this;
        }

        public Criteria andExecutestarttimeLessThanOrEqualTo(Date value) {
            addCriterion("ExecuteStartTime <=", value, "executestarttime");
            return (Criteria) this;
        }

        public Criteria andExecutestarttimeIn(List<Date> values) {
            addCriterion("ExecuteStartTime in", values, "executestarttime");
            return (Criteria) this;
        }

        public Criteria andExecutestarttimeNotIn(List<Date> values) {
            addCriterion("ExecuteStartTime not in", values, "executestarttime");
            return (Criteria) this;
        }

        public Criteria andExecutestarttimeBetween(Date value1, Date value2) {
            addCriterion("ExecuteStartTime between", value1, value2, "executestarttime");
            return (Criteria) this;
        }

        public Criteria andExecutestarttimeNotBetween(Date value1, Date value2) {
            addCriterion("ExecuteStartTime not between", value1, value2, "executestarttime");
            return (Criteria) this;
        }

        public Criteria andExecuteendtimeIsNull() {
            addCriterion("ExecuteEndTime is null");
            return (Criteria) this;
        }

        public Criteria andExecuteendtimeIsNotNull() {
            addCriterion("ExecuteEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteendtimeEqualTo(Date value) {
            addCriterion("ExecuteEndTime =", value, "executeendtime");
            return (Criteria) this;
        }

        public Criteria andExecuteendtimeNotEqualTo(Date value) {
            addCriterion("ExecuteEndTime <>", value, "executeendtime");
            return (Criteria) this;
        }

        public Criteria andExecuteendtimeGreaterThan(Date value) {
            addCriterion("ExecuteEndTime >", value, "executeendtime");
            return (Criteria) this;
        }

        public Criteria andExecuteendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ExecuteEndTime >=", value, "executeendtime");
            return (Criteria) this;
        }

        public Criteria andExecuteendtimeLessThan(Date value) {
            addCriterion("ExecuteEndTime <", value, "executeendtime");
            return (Criteria) this;
        }

        public Criteria andExecuteendtimeLessThanOrEqualTo(Date value) {
            addCriterion("ExecuteEndTime <=", value, "executeendtime");
            return (Criteria) this;
        }

        public Criteria andExecuteendtimeIn(List<Date> values) {
            addCriterion("ExecuteEndTime in", values, "executeendtime");
            return (Criteria) this;
        }

        public Criteria andExecuteendtimeNotIn(List<Date> values) {
            addCriterion("ExecuteEndTime not in", values, "executeendtime");
            return (Criteria) this;
        }

        public Criteria andExecuteendtimeBetween(Date value1, Date value2) {
            addCriterion("ExecuteEndTime between", value1, value2, "executeendtime");
            return (Criteria) this;
        }

        public Criteria andExecuteendtimeNotBetween(Date value1, Date value2) {
            addCriterion("ExecuteEndTime not between", value1, value2, "executeendtime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("Status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("Status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("Status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("Status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("Status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("Status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("Status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("Status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("Status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("Status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("Status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("Status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andFinishstatusIsNull() {
            addCriterion("FinishStatus is null");
            return (Criteria) this;
        }

        public Criteria andFinishstatusIsNotNull() {
            addCriterion("FinishStatus is not null");
            return (Criteria) this;
        }

        public Criteria andFinishstatusEqualTo(Integer value) {
            addCriterion("FinishStatus =", value, "finishstatus");
            return (Criteria) this;
        }

        public Criteria andFinishstatusNotEqualTo(Integer value) {
            addCriterion("FinishStatus <>", value, "finishstatus");
            return (Criteria) this;
        }

        public Criteria andFinishstatusGreaterThan(Integer value) {
            addCriterion("FinishStatus >", value, "finishstatus");
            return (Criteria) this;
        }

        public Criteria andFinishstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("FinishStatus >=", value, "finishstatus");
            return (Criteria) this;
        }

        public Criteria andFinishstatusLessThan(Integer value) {
            addCriterion("FinishStatus <", value, "finishstatus");
            return (Criteria) this;
        }

        public Criteria andFinishstatusLessThanOrEqualTo(Integer value) {
            addCriterion("FinishStatus <=", value, "finishstatus");
            return (Criteria) this;
        }

        public Criteria andFinishstatusIn(List<Integer> values) {
            addCriterion("FinishStatus in", values, "finishstatus");
            return (Criteria) this;
        }

        public Criteria andFinishstatusNotIn(List<Integer> values) {
            addCriterion("FinishStatus not in", values, "finishstatus");
            return (Criteria) this;
        }

        public Criteria andFinishstatusBetween(Integer value1, Integer value2) {
            addCriterion("FinishStatus between", value1, value2, "finishstatus");
            return (Criteria) this;
        }

        public Criteria andFinishstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("FinishStatus not between", value1, value2, "finishstatus");
            return (Criteria) this;
        }

        public Criteria andImgfolderIsNull() {
            addCriterion("ImgFolder is null");
            return (Criteria) this;
        }

        public Criteria andImgfolderIsNotNull() {
            addCriterion("ImgFolder is not null");
            return (Criteria) this;
        }

        public Criteria andImgfolderEqualTo(String value) {
            addCriterion("ImgFolder =", value, "imgfolder");
            return (Criteria) this;
        }

        public Criteria andImgfolderNotEqualTo(String value) {
            addCriterion("ImgFolder <>", value, "imgfolder");
            return (Criteria) this;
        }

        public Criteria andImgfolderGreaterThan(String value) {
            addCriterion("ImgFolder >", value, "imgfolder");
            return (Criteria) this;
        }

        public Criteria andImgfolderGreaterThanOrEqualTo(String value) {
            addCriterion("ImgFolder >=", value, "imgfolder");
            return (Criteria) this;
        }

        public Criteria andImgfolderLessThan(String value) {
            addCriterion("ImgFolder <", value, "imgfolder");
            return (Criteria) this;
        }

        public Criteria andImgfolderLessThanOrEqualTo(String value) {
            addCriterion("ImgFolder <=", value, "imgfolder");
            return (Criteria) this;
        }

        public Criteria andImgfolderLike(String value) {
            addCriterion("ImgFolder like", value, "imgfolder");
            return (Criteria) this;
        }

        public Criteria andImgfolderNotLike(String value) {
            addCriterion("ImgFolder not like", value, "imgfolder");
            return (Criteria) this;
        }

        public Criteria andImgfolderIn(List<String> values) {
            addCriterion("ImgFolder in", values, "imgfolder");
            return (Criteria) this;
        }

        public Criteria andImgfolderNotIn(List<String> values) {
            addCriterion("ImgFolder not in", values, "imgfolder");
            return (Criteria) this;
        }

        public Criteria andImgfolderBetween(String value1, String value2) {
            addCriterion("ImgFolder between", value1, value2, "imgfolder");
            return (Criteria) this;
        }

        public Criteria andImgfolderNotBetween(String value1, String value2) {
            addCriterion("ImgFolder not between", value1, value2, "imgfolder");
            return (Criteria) this;
        }

        public Criteria andReporturlIsNull() {
            addCriterion("ReportUrl is null");
            return (Criteria) this;
        }

        public Criteria andReporturlIsNotNull() {
            addCriterion("ReportUrl is not null");
            return (Criteria) this;
        }

        public Criteria andReporturlEqualTo(String value) {
            addCriterion("ReportUrl =", value, "reporturl");
            return (Criteria) this;
        }

        public Criteria andReporturlNotEqualTo(String value) {
            addCriterion("ReportUrl <>", value, "reporturl");
            return (Criteria) this;
        }

        public Criteria andReporturlGreaterThan(String value) {
            addCriterion("ReportUrl >", value, "reporturl");
            return (Criteria) this;
        }

        public Criteria andReporturlGreaterThanOrEqualTo(String value) {
            addCriterion("ReportUrl >=", value, "reporturl");
            return (Criteria) this;
        }

        public Criteria andReporturlLessThan(String value) {
            addCriterion("ReportUrl <", value, "reporturl");
            return (Criteria) this;
        }

        public Criteria andReporturlLessThanOrEqualTo(String value) {
            addCriterion("ReportUrl <=", value, "reporturl");
            return (Criteria) this;
        }

        public Criteria andReporturlLike(String value) {
            addCriterion("ReportUrl like", value, "reporturl");
            return (Criteria) this;
        }

        public Criteria andReporturlNotLike(String value) {
            addCriterion("ReportUrl not like", value, "reporturl");
            return (Criteria) this;
        }

        public Criteria andReporturlIn(List<String> values) {
            addCriterion("ReportUrl in", values, "reporturl");
            return (Criteria) this;
        }

        public Criteria andReporturlNotIn(List<String> values) {
            addCriterion("ReportUrl not in", values, "reporturl");
            return (Criteria) this;
        }

        public Criteria andReporturlBetween(String value1, String value2) {
            addCriterion("ReportUrl between", value1, value2, "reporturl");
            return (Criteria) this;
        }

        public Criteria andReporturlNotBetween(String value1, String value2) {
            addCriterion("ReportUrl not between", value1, value2, "reporturl");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}