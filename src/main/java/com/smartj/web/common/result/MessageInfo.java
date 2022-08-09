package com.smartj.web.common.result;

/**
 * <h1>消息接口</h1>
 * 消息接口定义了两个方法:String getCode()和 String getMessage()<BR/>
 * * getCode()返回编码，对应接口调用时返回成功或者失败的返回码或者错误码<BR/>
 * * getMessage()返回文字描述信息，对应接口调用时的成功或者失败的描述，或者详细的异常信息，用于展示<P/>
 * <h2>使用</h2>
 * 本接口常用于构造Result类。 <BR/>
 * 由于 MessageInfo 是一个接口，其实现就非常灵活。子类可以是枚举，异常。 <BR/>
 * * 应用系统中，对于一些既定的场景，可以预置一些实现 MessageInfo 的 枚举类 MessageEnum。<BR/>
 * * 当代码执行时，出现特定场景时，返回对应的结果，此时用 MessageEnum来构造返回消息 Result或者异常(比如业务异常BusinessException)<BR/>
 * * 而 BusinessException 也应该是实现 MessageInfo的，程序中抛出该异常后，在外围被捕捉，这时，又可以用 BusinessException 来构造返回消息Result
 */
public interface MessageInfo {
    /**
     * 子类（枚举）定义code，通过该方法返回给调用方
     * 常见的场景有，在异常处理中，需要创建一个指定场景的异常。
     * 而该场景对应的是一个枚举类型。
     * 枚举实现了MessageInfo接口，异常的构造方法就可以通过 getCode()来赋值
     *
     * @return 子类中定义的code
     */
    String getCode();

    /**
     * 与getCode类似，子类（枚举）定义message，通过该方法返回给调用方
     * 常见的场景有，在异常处理中，需要创建一个指定场景的异常。
     * 而该场景对应的是一个枚举类型。
     * 枚举实现了MessageInfo接口，异常的构造方法就可以通过 getMessage()来赋值
     *
     * @return 子类中定义的message
     */
    String getMessage();
}
