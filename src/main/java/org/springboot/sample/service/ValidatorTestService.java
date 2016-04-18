/*
 * Copyright 2012-2013 the original author or authors.
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

package org.springboot.sample.service;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated // 告诉MethodValidationPostProcessor此Bean需要开启方法级别验证支持  
@Service
public class ValidatorTestService {

	public @Length(min = 12, max = 16, message = "返回值长度应该为12-16") 
		String getContent(
			@NotBlank(message = "name不能为空")
			String name,
			@Size(min = 5, max = 10, message="{password.length.illegal}") 
			String password) {
		return name + ":" + password;
	}

}
