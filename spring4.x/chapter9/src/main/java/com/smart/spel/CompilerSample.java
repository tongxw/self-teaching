package com.smart.spel;


import com.smart.User;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class CompilerSample {

    public static void main(String[] args) {
        User user = new User();
        // 创建解析配置
        SpelParserConfiguration configuration =
                new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, CompilerSample.class.getClassLoader());

        // 创建解析器
        SpelExpressionParser parser = new SpelExpressionParser(configuration);

        // 创建取值上下文（根据User Object）
        EvaluationContext context = new StandardEvaluationContext(user);

        // 表达式
        String expression = "isVipMember('tom') && isVipMember('jony')";

        // 解析表达式
        SpelExpression spelExpression = parser.parseRaw(expression);

        // 表达式求值
        System.out.println(spelExpression.getValue(context));
        System.out.println(spelExpression.getValue(context));
    }
}
