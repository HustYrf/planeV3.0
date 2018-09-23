package hust.plane.mapper.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andPasswordIsNull() {
            addCriterion("PassWord is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("PassWord is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("PassWord =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("PassWord <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("PassWord >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("PassWord >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("PassWord <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("PassWord <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("PassWord like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("PassWord not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("PassWord in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("PassWord not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("PassWord between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("PassWord not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("NickName is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("NickName is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("NickName =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("NickName <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("NickName >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("NickName >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("NickName <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("NickName <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("NickName like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("NickName not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("NickName in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("NickName not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("NickName between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("NickName not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andWorknumberIsNull() {
            addCriterion("WorkNumber is null");
            return (Criteria) this;
        }

        public Criteria andWorknumberIsNotNull() {
            addCriterion("WorkNumber is not null");
            return (Criteria) this;
        }

        public Criteria andWorknumberEqualTo(String value) {
            addCriterion("WorkNumber =", value, "worknumber");
            return (Criteria) this;
        }

        public Criteria andWorknumberNotEqualTo(String value) {
            addCriterion("WorkNumber <>", value, "worknumber");
            return (Criteria) this;
        }

        public Criteria andWorknumberGreaterThan(String value) {
            addCriterion("WorkNumber >", value, "worknumber");
            return (Criteria) this;
        }

        public Criteria andWorknumberGreaterThanOrEqualTo(String value) {
            addCriterion("WorkNumber >=", value, "worknumber");
            return (Criteria) this;
        }

        public Criteria andWorknumberLessThan(String value) {
            addCriterion("WorkNumber <", value, "worknumber");
            return (Criteria) this;
        }

        public Criteria andWorknumberLessThanOrEqualTo(String value) {
            addCriterion("WorkNumber <=", value, "worknumber");
            return (Criteria) this;
        }

        public Criteria andWorknumberLike(String value) {
            addCriterion("WorkNumber like", value, "worknumber");
            return (Criteria) this;
        }

        public Criteria andWorknumberNotLike(String value) {
            addCriterion("WorkNumber not like", value, "worknumber");
            return (Criteria) this;
        }

        public Criteria andWorknumberIn(List<String> values) {
            addCriterion("WorkNumber in", values, "worknumber");
            return (Criteria) this;
        }

        public Criteria andWorknumberNotIn(List<String> values) {
            addCriterion("WorkNumber not in", values, "worknumber");
            return (Criteria) this;
        }

        public Criteria andWorknumberBetween(String value1, String value2) {
            addCriterion("WorkNumber between", value1, value2, "worknumber");
            return (Criteria) this;
        }

        public Criteria andWorknumberNotBetween(String value1, String value2) {
            addCriterion("WorkNumber not between", value1, value2, "worknumber");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("Email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("Email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("Email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("Email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("Email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("Email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("Email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("Email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("Email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("Email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("Email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("Email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("Email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("Email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("CreateTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("CreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("CreateTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("CreateTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("CreateTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CreateTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("CreateTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("CreateTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("CreateTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("CreateTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("CreateTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("CreateTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("UpdateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("UpdateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("UpdateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("UpdateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("UpdateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UpdateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("UpdateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("UpdateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("UpdateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("UpdateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("UpdateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("UpdateTime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNull() {
            addCriterion("Department_id is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("Department_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdEqualTo(Integer value) {
            addCriterion("Department_id =", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotEqualTo(Integer value) {
            addCriterion("Department_id <>", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThan(Integer value) {
            addCriterion("Department_id >", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Department_id >=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThan(Integer value) {
            addCriterion("Department_id <", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("Department_id <=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIn(List<Integer> values) {
            addCriterion("Department_id in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotIn(List<Integer> values) {
            addCriterion("Department_id not in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdBetween(Integer value1, Integer value2) {
            addCriterion("Department_id between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Department_id not between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andPhoneoneIsNull() {
            addCriterion("PhoneOne is null");
            return (Criteria) this;
        }

        public Criteria andPhoneoneIsNotNull() {
            addCriterion("PhoneOne is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneoneEqualTo(String value) {
            addCriterion("PhoneOne =", value, "phoneone");
            return (Criteria) this;
        }

        public Criteria andPhoneoneNotEqualTo(String value) {
            addCriterion("PhoneOne <>", value, "phoneone");
            return (Criteria) this;
        }

        public Criteria andPhoneoneGreaterThan(String value) {
            addCriterion("PhoneOne >", value, "phoneone");
            return (Criteria) this;
        }

        public Criteria andPhoneoneGreaterThanOrEqualTo(String value) {
            addCriterion("PhoneOne >=", value, "phoneone");
            return (Criteria) this;
        }

        public Criteria andPhoneoneLessThan(String value) {
            addCriterion("PhoneOne <", value, "phoneone");
            return (Criteria) this;
        }

        public Criteria andPhoneoneLessThanOrEqualTo(String value) {
            addCriterion("PhoneOne <=", value, "phoneone");
            return (Criteria) this;
        }

        public Criteria andPhoneoneLike(String value) {
            addCriterion("PhoneOne like", value, "phoneone");
            return (Criteria) this;
        }

        public Criteria andPhoneoneNotLike(String value) {
            addCriterion("PhoneOne not like", value, "phoneone");
            return (Criteria) this;
        }

        public Criteria andPhoneoneIn(List<String> values) {
            addCriterion("PhoneOne in", values, "phoneone");
            return (Criteria) this;
        }

        public Criteria andPhoneoneNotIn(List<String> values) {
            addCriterion("PhoneOne not in", values, "phoneone");
            return (Criteria) this;
        }

        public Criteria andPhoneoneBetween(String value1, String value2) {
            addCriterion("PhoneOne between", value1, value2, "phoneone");
            return (Criteria) this;
        }

        public Criteria andPhoneoneNotBetween(String value1, String value2) {
            addCriterion("PhoneOne not between", value1, value2, "phoneone");
            return (Criteria) this;
        }

        public Criteria andPhonetwoIsNull() {
            addCriterion("PhoneTwo is null");
            return (Criteria) this;
        }

        public Criteria andPhonetwoIsNotNull() {
            addCriterion("PhoneTwo is not null");
            return (Criteria) this;
        }

        public Criteria andPhonetwoEqualTo(String value) {
            addCriterion("PhoneTwo =", value, "phonetwo");
            return (Criteria) this;
        }

        public Criteria andPhonetwoNotEqualTo(String value) {
            addCriterion("PhoneTwo <>", value, "phonetwo");
            return (Criteria) this;
        }

        public Criteria andPhonetwoGreaterThan(String value) {
            addCriterion("PhoneTwo >", value, "phonetwo");
            return (Criteria) this;
        }

        public Criteria andPhonetwoGreaterThanOrEqualTo(String value) {
            addCriterion("PhoneTwo >=", value, "phonetwo");
            return (Criteria) this;
        }

        public Criteria andPhonetwoLessThan(String value) {
            addCriterion("PhoneTwo <", value, "phonetwo");
            return (Criteria) this;
        }

        public Criteria andPhonetwoLessThanOrEqualTo(String value) {
            addCriterion("PhoneTwo <=", value, "phonetwo");
            return (Criteria) this;
        }

        public Criteria andPhonetwoLike(String value) {
            addCriterion("PhoneTwo like", value, "phonetwo");
            return (Criteria) this;
        }

        public Criteria andPhonetwoNotLike(String value) {
            addCriterion("PhoneTwo not like", value, "phonetwo");
            return (Criteria) this;
        }

        public Criteria andPhonetwoIn(List<String> values) {
            addCriterion("PhoneTwo in", values, "phonetwo");
            return (Criteria) this;
        }

        public Criteria andPhonetwoNotIn(List<String> values) {
            addCriterion("PhoneTwo not in", values, "phonetwo");
            return (Criteria) this;
        }

        public Criteria andPhonetwoBetween(String value1, String value2) {
            addCriterion("PhoneTwo between", value1, value2, "phonetwo");
            return (Criteria) this;
        }

        public Criteria andPhonetwoNotBetween(String value1, String value2) {
            addCriterion("PhoneTwo not between", value1, value2, "phonetwo");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("Icon is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("Icon is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("Icon =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("Icon <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("Icon >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("Icon >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("Icon <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("Icon <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("Icon like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("Icon not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("Icon in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("Icon not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("Icon between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("Icon not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andTasknumIsNull() {
            addCriterion("TaskNum is null");
            return (Criteria) this;
        }

        public Criteria andTasknumIsNotNull() {
            addCriterion("TaskNum is not null");
            return (Criteria) this;
        }

        public Criteria andTasknumEqualTo(Integer value) {
            addCriterion("TaskNum =", value, "tasknum");
            return (Criteria) this;
        }

        public Criteria andTasknumNotEqualTo(Integer value) {
            addCriterion("TaskNum <>", value, "tasknum");
            return (Criteria) this;
        }

        public Criteria andTasknumGreaterThan(Integer value) {
            addCriterion("TaskNum >", value, "tasknum");
            return (Criteria) this;
        }

        public Criteria andTasknumGreaterThanOrEqualTo(Integer value) {
            addCriterion("TaskNum >=", value, "tasknum");
            return (Criteria) this;
        }

        public Criteria andTasknumLessThan(Integer value) {
            addCriterion("TaskNum <", value, "tasknum");
            return (Criteria) this;
        }

        public Criteria andTasknumLessThanOrEqualTo(Integer value) {
            addCriterion("TaskNum <=", value, "tasknum");
            return (Criteria) this;
        }

        public Criteria andTasknumIn(List<Integer> values) {
            addCriterion("TaskNum in", values, "tasknum");
            return (Criteria) this;
        }

        public Criteria andTasknumNotIn(List<Integer> values) {
            addCriterion("TaskNum not in", values, "tasknum");
            return (Criteria) this;
        }

        public Criteria andTasknumBetween(Integer value1, Integer value2) {
            addCriterion("TaskNum between", value1, value2, "tasknum");
            return (Criteria) this;
        }

        public Criteria andTasknumNotBetween(Integer value1, Integer value2) {
            addCriterion("TaskNum not between", value1, value2, "tasknum");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("Description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("Description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("Description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("Description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("Description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("Description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("Description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("Description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("Description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("Description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("Description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("Description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("Description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("Description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andHistorypointIsNull() {
            addCriterion("HistoryPoint is null");
            return (Criteria) this;
        }

        public Criteria andHistorypointIsNotNull() {
            addCriterion("HistoryPoint is not null");
            return (Criteria) this;
        }

        public Criteria andHistorypointEqualTo(Object value) {
            addCriterion("HistoryPoint =", value, "historypoint");
            return (Criteria) this;
        }

        public Criteria andHistorypointNotEqualTo(Object value) {
            addCriterion("HistoryPoint <>", value, "historypoint");
            return (Criteria) this;
        }

        public Criteria andHistorypointGreaterThan(Object value) {
            addCriterion("HistoryPoint >", value, "historypoint");
            return (Criteria) this;
        }

        public Criteria andHistorypointGreaterThanOrEqualTo(Object value) {
            addCriterion("HistoryPoint >=", value, "historypoint");
            return (Criteria) this;
        }

        public Criteria andHistorypointLessThan(Object value) {
            addCriterion("HistoryPoint <", value, "historypoint");
            return (Criteria) this;
        }

        public Criteria andHistorypointLessThanOrEqualTo(Object value) {
            addCriterion("HistoryPoint <=", value, "historypoint");
            return (Criteria) this;
        }

        public Criteria andHistorypointIn(List<Object> values) {
            addCriterion("HistoryPoint in", values, "historypoint");
            return (Criteria) this;
        }

        public Criteria andHistorypointNotIn(List<Object> values) {
            addCriterion("HistoryPoint not in", values, "historypoint");
            return (Criteria) this;
        }

        public Criteria andHistorypointBetween(Object value1, Object value2) {
            addCriterion("HistoryPoint between", value1, value2, "historypoint");
            return (Criteria) this;
        }

        public Criteria andHistorypointNotBetween(Object value1, Object value2) {
            addCriterion("HistoryPoint not between", value1, value2, "historypoint");
            return (Criteria) this;
        }

        public Criteria andHistoryzoomIsNull() {
            addCriterion("HistoryZoom is null");
            return (Criteria) this;
        }

        public Criteria andHistoryzoomIsNotNull() {
            addCriterion("HistoryZoom is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryzoomEqualTo(Integer value) {
            addCriterion("HistoryZoom =", value, "historyzoom");
            return (Criteria) this;
        }

        public Criteria andHistoryzoomNotEqualTo(Integer value) {
            addCriterion("HistoryZoom <>", value, "historyzoom");
            return (Criteria) this;
        }

        public Criteria andHistoryzoomGreaterThan(Integer value) {
            addCriterion("HistoryZoom >", value, "historyzoom");
            return (Criteria) this;
        }

        public Criteria andHistoryzoomGreaterThanOrEqualTo(Integer value) {
            addCriterion("HistoryZoom >=", value, "historyzoom");
            return (Criteria) this;
        }

        public Criteria andHistoryzoomLessThan(Integer value) {
            addCriterion("HistoryZoom <", value, "historyzoom");
            return (Criteria) this;
        }

        public Criteria andHistoryzoomLessThanOrEqualTo(Integer value) {
            addCriterion("HistoryZoom <=", value, "historyzoom");
            return (Criteria) this;
        }

        public Criteria andHistoryzoomIn(List<Integer> values) {
            addCriterion("HistoryZoom in", values, "historyzoom");
            return (Criteria) this;
        }

        public Criteria andHistoryzoomNotIn(List<Integer> values) {
            addCriterion("HistoryZoom not in", values, "historyzoom");
            return (Criteria) this;
        }

        public Criteria andHistoryzoomBetween(Integer value1, Integer value2) {
            addCriterion("HistoryZoom between", value1, value2, "historyzoom");
            return (Criteria) this;
        }

        public Criteria andHistoryzoomNotBetween(Integer value1, Integer value2) {
            addCriterion("HistoryZoom not between", value1, value2, "historyzoom");
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