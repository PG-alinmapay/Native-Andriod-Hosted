# AlinmaPay Native Android SDK Integration

This repository contains the **Native Android Demo Application** and **Android Hosted Plugin** for integrating the **AlinmaPay Payment Gateway** into Android mobile applications.

---

## Overview

The AlinmaPay Android Hosted SDK enables merchants to:

- Accept secure online payments
- Support credit/debit cards and tokenized transactions
- Initiate refunds, voids, and transaction enquiries
- Ensure compliance with payment security standards

---

## Requirements

| Component | Version |
|-----------|---------|
| Android Studio | 4.0.1+ |
| Android SDK | 34 |
| Minimum Android | 4.4 (API 21) |
| Maximum Supported | Android 16 (API 36) |

---

## Prerequisites

Before integration, ensure you have:

- AlinmaPay **Merchant Dashboard account**
- **Terminal ID**
- **Terminal Password**
- **Merchant Key**
- **Service URL**

⚠️ Keep merchant credentials **secure and confidential**.

---

## SDK Installation

1. Copy the provided **`.aar` plugin file** into:

```
app/libs/
```

2. Open **module `build.gradle`** and add:

```gradle
implementation files('libs/android_hosted_plugin.aar')
implementation 'com.android.volley:volley:1.1.0'
```

3. Sync the project.

---

## Configuration

Create **`appconfig.json`** inside the **assets** folder:

```json
{
  "merchantKey": "YOUR_MERCHANT_KEY",
  "terminalId": "YOUR_TERMINAL_ID",
  "terminalPass": "YOUR_TERMINAL_PASSWORD",
  "requestUrl": "SERVICE_URL"
}
```

Also copy:

```
gradle-wrapper.properties → assets folder
```

---

## Initiating a Payment

Initialize SDK in your Activity:

```java
import com.data.android_direct_plugin.TrxnPayments;

TrxnPayments trxnPayments = new TrxnPayments();

trxnPayments.makepaymentService(
    MainActivity.this,
    amount,
    actionCode,
    currency,
    email,
    address,
    city,
    state,
    zip,
    countryCode,
    trackId,
    operation,
    cardToken,
    tokenType,
    cardHolderName,
    cardNumber,
    cardCVV,
    expMonth,
    expYear,
    transactionId,
    metadata
);
```

---

## Handling Payment Response

Handle response inside **`onActivityResult`**:

```java
String message = data.getStringExtra("MESSAGE");

JSONObject jsonObj = new JSONObject(message);

Intent intent = new Intent(MainActivity.this, ReceiptPage.class);
intent.putExtra("jsonObject", jsonObj.toString());
startActivity(intent);
finish();
```

---

## Supported Transactions

- Purchase  
- Pre‑Authorization  
- Void Purchase / Auth  
- Refund / Void Refund  
- Tokenization (Add / Update / Delete)  
- Purchase with Token  
- Pre‑Auth with Token  
- Transaction Enquiry  

---

## Author

**AlinmaPay Integration Team**

---

## License

Proprietary – Provided for authorized merchant integration only.
