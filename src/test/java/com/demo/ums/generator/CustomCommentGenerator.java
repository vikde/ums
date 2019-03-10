package com.demo.ums.generator;

import org.joda.time.DateTime;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * @author vikde
 * @date 2017/12/27
 */
public class CustomCommentGenerator extends DefaultCommentGenerator {
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine("/**");
        String remarks = introspectedColumn.getRemarks();
        field.addJavaDocLine(" *" + (remarks == null ? "" : remarks.replace("\n", " ")));
        field.addJavaDocLine(" */");
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * @author mybatis-generator");
        topLevelClass.addJavaDocLine(" * @date " + DateTime.now().toString("yyyy/MM/dd"));
        topLevelClass.addJavaDocLine(" */");
    }
}
