/*
 * Copyright (C) 2017 zhengjun, fanwe (http://www.fanwe.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.linhome.lib.im.msg;

/**
 * IM消息数据
 *
 * @param <M> 第三方IM消息类型
 */
public interface FIMMsgData<M>
{
    /**
     * 返回数据类型
     *
     * @return
     */
    int getType();

    /**
     * 将当前数据解析为第三方SDK的消息
     *
     * @return
     */
    M parseToSDKMsg();

    /**
     * 将数据解析为FIM消息
     *
     * @return
     */
    FIMMsg parseToMsg();
}
