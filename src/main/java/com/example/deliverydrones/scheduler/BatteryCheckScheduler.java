package com.example.deliverydrones.scheduler;

import com.example.deliverydrones.service.BatteryHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatteryCheckScheduler {

    private final BatteryHistoryService batteryHistoryService;

    @Scheduled(cron = "${app.battery.check.schedule}")
    public void process() {
        batteryHistoryService.updateHistory();
    }
}
