/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.wangzijie.server.web;

import cc.wangzijie.server.entity.SysUser;
import cc.wangzijie.server.service.ISysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private ISysUserService sysUserService;

    @GetMapping("/now")
    public String now() {
        return sysUserService.now();
    }

    @GetMapping("/all")
    public List<SysUser> getList() {
        return sysUserService.voList(new SysUser());
    }

    @PostMapping("/list")
    public List<SysUser> getList(@RequestBody SysUser entity) {
        return sysUserService.voList(entity);
    }

    @GetMapping("/get")
    public SysUser voGet(@RequestParam Long id) {
        return sysUserService.voGetById(id);
    }

    @PostMapping("/create")
    public boolean create(@RequestBody SysUser entity) {
        return sysUserService.save(entity);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody SysUser entity) {
        return sysUserService.updateById(entity);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody List<Long> ids) {
        return sysUserService.removeByIds(ids);
    }

}
