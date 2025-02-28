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

import cc.wangzijie.server.entity.OcrResult;
import cc.wangzijie.server.service.IOcrResultService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/ocr-result")
public class OcrResultController {

    @Resource
    private IOcrResultService ocrResultService;

    @GetMapping("/all")
    public List<OcrResult> getList() {
        return ocrResultService.getList(new OcrResult());
    }

    @PostMapping("/list")
    public List<OcrResult> getList(@RequestBody OcrResult entity) {
        return ocrResultService.getList(entity);
    }

    @GetMapping("/get")
    public OcrResult voGet(@RequestParam Long id) {
        return ocrResultService.getById(id);
    }

    @PostMapping("/create")
    public boolean create(@RequestBody OcrResult entity) {
        return ocrResultService.save(entity);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody OcrResult entity) {
        return ocrResultService.updateById(entity);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody List<Long> ids) {
        return ocrResultService.removeByIds(ids);
    }

}
