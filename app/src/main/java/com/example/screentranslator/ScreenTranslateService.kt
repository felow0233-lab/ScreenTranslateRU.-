private fun startForegroundNotification(): Notification {
        val channelId = "screen_translator_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Screen Translator",
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Переводчик экрана активен")
            .setContentText("Нажмите на плавающую кнопку для переключения")
            .setSmallIcon(android.R.drawable.ic_menu_search)
            .build()
        startForeground(1, notification)
        return notification
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        virtualDisplay?.release()
        mediaProjection?.stop()
        if (::floatingButton.isInitialized) windowManager.removeView(floatingButton)
        if (::overlayView.isInitialized) windowManager.removeView(overlayView)
    }
}
