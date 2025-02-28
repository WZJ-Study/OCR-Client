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

import cc.wangzijie.server.entity.OcrSection;
import cc.wangzijie.server.service.IOcrSectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/ocr-section")
public class OcrSectionController {

    @Resource
    private IOcrSectionService ocrSectionService;

    @GetMapping("/all")
    public List<OcrSection> getList() {
        return ocrSectionService.getList(new OcrSection());
    }

    @PostMapping("/list")
    public List<OcrSection> getList(@RequestBody OcrSection entity) {
        return ocrSectionService.getList(entity);
    }

    @GetMapping("/get")
    public OcrSection voGet(@RequestParam Long id) {
        return ocrSectionService.getById(id);
    }

    @PostMapping("/create")
    public boolean create(@RequestBody OcrSection entity) {
        return ocrSectionService.save(entity);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody OcrSection entity) {
        return ocrSectionService.updateById(entity);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody List<Long> ids) {
        return ocrSectionService.removeByIds(ids);
    }

}
