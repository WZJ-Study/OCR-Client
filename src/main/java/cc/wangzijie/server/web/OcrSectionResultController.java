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

import cc.wangzijie.server.entity.OcrSectionResult;
import cc.wangzijie.server.service.IOcrSectionResultService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/ocr-section-result")
public class OcrSectionResultController {

    @Resource
    private IOcrSectionResultService ocrResultService;

    @GetMapping("/all")
    public List<OcrSectionResult> getList() {
        return ocrResultService.getList(new OcrSectionResult());
    }

    @PostMapping("/list")
    public List<OcrSectionResult> getList(@RequestBody OcrSectionResult entity) {
        return ocrResultService.getList(entity);
    }

    @GetMapping("/get")
    public OcrSectionResult voGet(@RequestParam Long id) {
        return ocrResultService.getById(id);
    }

    @PostMapping("/create")
    public boolean create(@RequestBody OcrSectionResult entity) {
        return ocrResultService.save(entity);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody OcrSectionResult entity) {
        return ocrResultService.updateById(entity);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody List<Long> ids) {
        return ocrResultService.removeByIds(ids);
    }

}
