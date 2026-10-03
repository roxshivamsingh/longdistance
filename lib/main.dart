import 'dart:async';

import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

void main() {
  runApp(const LongDistanceApp());
}

class LongDistanceApp extends StatelessWidget {
  const LongDistanceApp({super.key});

  @override
  Widget build(BuildContext context) {
    const primary = Color(0xFFBE5671);

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        scaffoldBackgroundColor: const Color(0xFFFFF7F5),
        textTheme: GoogleFonts.poppinsTextTheme(),
        colorScheme: ColorScheme.fromSeed(seedColor: primary),
      ),
      home: const DurationScreen(),
    );
  }
}

class DurationScreen extends StatefulWidget {
  const DurationScreen({super.key});

  @override
  State<DurationScreen> createState() => _DurationScreenState();
}

class _DurationScreenState extends State<DurationScreen> {
  static final DateTime startDate = DateTime(2025, 12, 24);

  late DateTime now;
  Timer? timer;

  @override
  void initState() {
    super.initState();
    now = DateTime.now();

    timer = Timer.periodic(const Duration(minutes: 1), (_) {
      if (mounted) {
        setState(() => now = DateTime.now());
      }
    });
  }

  @override
  void dispose() {
    timer?.cancel();
    super.dispose();
  }

  Map<String, int> get duration {
    var months = (now.year - startDate.year) * 12 + now.month - startDate.month;

    var anchor = DateTime(
      startDate.year,
      startDate.month + months,
      startDate.day,
    );

    if (anchor.isAfter(now)) {
      months--;
      anchor = DateTime(
        startDate.year,
        startDate.month + months,
        startDate.day,
      );
    }

    return {'months': months, 'days': now.difference(anchor).inDays};
  }

  String formatDate(DateTime date) {
    final day = date.day.toString().padLeft(2, '0');
    final month = date.month.toString().padLeft(2, '0');
    return '$day/$month/${date.year}';
  }

  @override
  Widget build(BuildContext context) {
    const primary = Color(0xFFBE5671);
    const textColor = Color(0xFF3D2930);
    const muted = Color(0xFF927B83);

    final months = duration['months']!;
    final days = duration['days']!;

    return Scaffold(
      body: SafeArea(
        child: Center(
          child: SingleChildScrollView(
            padding: const EdgeInsets.all(24),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  'longdistance',
                  style: GoogleFonts.pacifico(fontSize: 38, color: primary),
                ),
                const SizedBox(height: 8),
                Text(
                  'TWO HEARTS, ONE JOURNEY',
                  style: GoogleFonts.poppins(
                    fontSize: 10,
                    fontWeight: FontWeight.w600,
                    letterSpacing: 2.5,
                    color: muted,
                  ),
                ),
                const SizedBox(height: 36),

                // One unified duration card
                Container(
                  width: double.infinity,
                  padding: const EdgeInsets.all(28),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(28),
                    border: Border.all(color: const Color(0xFFF0DCE2)),
                    boxShadow: [
                      BoxShadow(
                        color: primary.withValues(alpha: 0.10),
                        blurRadius: 30,
                        offset: const Offset(0, 12),
                      ),
                    ],
                  ),
                  child: Column(
                    children: [
                      Container(
                        width: 64,
                        height: 64,
                        decoration: const BoxDecoration(
                          color: Color(0xFFFFEDF1),
                          shape: BoxShape.circle,
                        ),
                        child: const Icon(
                          Icons.heart_broken_rounded,
                          color: primary,
                          size: 30,
                        ),
                      ),
                      const SizedBox(height: 22),
                      Text(
                        'OUR JOURNEY',
                        style: GoogleFonts.poppins(
                          fontSize: 11,
                          fontWeight: FontWeight.w600,
                          letterSpacing: 2.5,
                          color: muted,
                        ),
                      ),
                      const SizedBox(height: 10),
                      Text(
                        '${formatDate(startDate)}  —  ${formatDate(now)}',
                        textAlign: TextAlign.center,
                        style: GoogleFonts.poppins(
                          fontSize: 13,
                          fontWeight: FontWeight.w500,
                          color: textColor,
                        ),
                      ),
                      const SizedBox(height: 30),

                      // Months and days together
                      RichText(
                        textAlign: TextAlign.center,
                        text: TextSpan(
                          style: GoogleFonts.poppins(
                            color: primary,
                            fontWeight: FontWeight.w600,
                          ),
                          children: [
                            TextSpan(
                              text: '$months',
                              style: const TextStyle(fontSize: 48),
                            ),
                            TextSpan(
                              text: months == 1 ? ' month ' : ' months ',
                              style: const TextStyle(fontSize: 17),
                            ),
                            TextSpan(
                              text: '$days',
                              style: const TextStyle(fontSize: 48),
                            ),
                            TextSpan(
                              text: days == 1 ? ' day' : ' days',
                              style: const TextStyle(fontSize: 17),
                            ),
                          ],
                        ),
                      ),

                      const SizedBox(height: 12),
                      Container(
                        width: 55,
                        height: 3,
                        decoration: BoxDecoration(
                          color: const Color(0xFFF0C5D1),
                          borderRadius: BorderRadius.circular(10),
                        ),
                      ),
                      const SizedBox(height: 24),
                      Text(
                        'Every day, a little closer. ♡',
                        textAlign: TextAlign.center,
                        style: GoogleFonts.poppins(fontSize: 13, color: muted),
                      ),
                    ],
                  ),
                ),

                const SizedBox(height: 28),
                const Icon(
                  Icons.auto_awesome_rounded,
                  color: Color(0xFFD9A0AF),
                  size: 20,
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
