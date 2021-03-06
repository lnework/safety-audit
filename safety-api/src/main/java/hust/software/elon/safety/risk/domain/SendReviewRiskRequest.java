/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package hust.software.elon.safety.risk.domain;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-05-11")
public class SendReviewRiskRequest implements org.apache.thrift.TBase<SendReviewRiskRequest, SendReviewRiskRequest._Fields>, java.io.Serializable, Cloneable, Comparable<SendReviewRiskRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SendReviewRiskRequest");

  private static final org.apache.thrift.protocol.TField OBJECT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("objectId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField OBJECT_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("objectType", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField CONFIG_KEY_FIELD_DESC = new org.apache.thrift.protocol.TField("configKey", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField TOKEN_FIELD_DESC = new org.apache.thrift.protocol.TField("token", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField FORCE_NOT_CACHE_FIELD_DESC = new org.apache.thrift.protocol.TField("forceNotCache", org.apache.thrift.protocol.TType.BOOL, (short)5);
  private static final org.apache.thrift.protocol.TField EXTRA_JSON_FIELD_DESC = new org.apache.thrift.protocol.TField("extraJson", org.apache.thrift.protocol.TType.STRING, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new SendReviewRiskRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new SendReviewRiskRequestTupleSchemeFactory();

  public long objectId; // required
  /**
   * 
   * @see hust.software.elon.safety.common.domain.ObjectType
   */
  public @org.apache.thrift.annotation.Nullable hust.software.elon.safety.common.domain.ObjectType objectType; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String configKey; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String token; // required
  public boolean forceNotCache; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String extraJson; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    OBJECT_ID((short)1, "objectId"),
    /**
     * 
     * @see hust.software.elon.safety.common.domain.ObjectType
     */
    OBJECT_TYPE((short)2, "objectType"),
    CONFIG_KEY((short)3, "configKey"),
    TOKEN((short)4, "token"),
    FORCE_NOT_CACHE((short)5, "forceNotCache"),
    EXTRA_JSON((short)6, "extraJson");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // OBJECT_ID
          return OBJECT_ID;
        case 2: // OBJECT_TYPE
          return OBJECT_TYPE;
        case 3: // CONFIG_KEY
          return CONFIG_KEY;
        case 4: // TOKEN
          return TOKEN;
        case 5: // FORCE_NOT_CACHE
          return FORCE_NOT_CACHE;
        case 6: // EXTRA_JSON
          return EXTRA_JSON;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __OBJECTID_ISSET_ID = 0;
  private static final int __FORCENOTCACHE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.OBJECT_ID, new org.apache.thrift.meta_data.FieldMetaData("objectId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.OBJECT_TYPE, new org.apache.thrift.meta_data.FieldMetaData("objectType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, hust.software.elon.safety.common.domain.ObjectType.class)));
    tmpMap.put(_Fields.CONFIG_KEY, new org.apache.thrift.meta_data.FieldMetaData("configKey", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TOKEN, new org.apache.thrift.meta_data.FieldMetaData("token", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FORCE_NOT_CACHE, new org.apache.thrift.meta_data.FieldMetaData("forceNotCache", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.EXTRA_JSON, new org.apache.thrift.meta_data.FieldMetaData("extraJson", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SendReviewRiskRequest.class, metaDataMap);
  }

  public SendReviewRiskRequest() {
  }

  public SendReviewRiskRequest(
    long objectId,
    hust.software.elon.safety.common.domain.ObjectType objectType,
    java.lang.String configKey,
    java.lang.String token,
    boolean forceNotCache,
    java.lang.String extraJson)
  {
    this();
    this.objectId = objectId;
    setObjectIdIsSet(true);
    this.objectType = objectType;
    this.configKey = configKey;
    this.token = token;
    this.forceNotCache = forceNotCache;
    setForceNotCacheIsSet(true);
    this.extraJson = extraJson;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SendReviewRiskRequest(SendReviewRiskRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    this.objectId = other.objectId;
    if (other.isSetObjectType()) {
      this.objectType = other.objectType;
    }
    if (other.isSetConfigKey()) {
      this.configKey = other.configKey;
    }
    if (other.isSetToken()) {
      this.token = other.token;
    }
    this.forceNotCache = other.forceNotCache;
    if (other.isSetExtraJson()) {
      this.extraJson = other.extraJson;
    }
  }

  public SendReviewRiskRequest deepCopy() {
    return new SendReviewRiskRequest(this);
  }

  @Override
  public void clear() {
    setObjectIdIsSet(false);
    this.objectId = 0;
    this.objectType = null;
    this.configKey = null;
    this.token = null;
    setForceNotCacheIsSet(false);
    this.forceNotCache = false;
    this.extraJson = null;
  }

  public long getObjectId() {
    return this.objectId;
  }

  public SendReviewRiskRequest setObjectId(long objectId) {
    this.objectId = objectId;
    setObjectIdIsSet(true);
    return this;
  }

  public void unsetObjectId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __OBJECTID_ISSET_ID);
  }

  /** Returns true if field objectId is set (has been assigned a value) and false otherwise */
  public boolean isSetObjectId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __OBJECTID_ISSET_ID);
  }

  public void setObjectIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __OBJECTID_ISSET_ID, value);
  }

  /**
   * 
   * @see hust.software.elon.safety.common.domain.ObjectType
   */
  @org.apache.thrift.annotation.Nullable
  public hust.software.elon.safety.common.domain.ObjectType getObjectType() {
    return this.objectType;
  }

  /**
   * 
   * @see hust.software.elon.safety.common.domain.ObjectType
   */
  public SendReviewRiskRequest setObjectType(@org.apache.thrift.annotation.Nullable hust.software.elon.safety.common.domain.ObjectType objectType) {
    this.objectType = objectType;
    return this;
  }

  public void unsetObjectType() {
    this.objectType = null;
  }

  /** Returns true if field objectType is set (has been assigned a value) and false otherwise */
  public boolean isSetObjectType() {
    return this.objectType != null;
  }

  public void setObjectTypeIsSet(boolean value) {
    if (!value) {
      this.objectType = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getConfigKey() {
    return this.configKey;
  }

  public SendReviewRiskRequest setConfigKey(@org.apache.thrift.annotation.Nullable java.lang.String configKey) {
    this.configKey = configKey;
    return this;
  }

  public void unsetConfigKey() {
    this.configKey = null;
  }

  /** Returns true if field configKey is set (has been assigned a value) and false otherwise */
  public boolean isSetConfigKey() {
    return this.configKey != null;
  }

  public void setConfigKeyIsSet(boolean value) {
    if (!value) {
      this.configKey = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getToken() {
    return this.token;
  }

  public SendReviewRiskRequest setToken(@org.apache.thrift.annotation.Nullable java.lang.String token) {
    this.token = token;
    return this;
  }

  public void unsetToken() {
    this.token = null;
  }

  /** Returns true if field token is set (has been assigned a value) and false otherwise */
  public boolean isSetToken() {
    return this.token != null;
  }

  public void setTokenIsSet(boolean value) {
    if (!value) {
      this.token = null;
    }
  }

  public boolean isForceNotCache() {
    return this.forceNotCache;
  }

  public SendReviewRiskRequest setForceNotCache(boolean forceNotCache) {
    this.forceNotCache = forceNotCache;
    setForceNotCacheIsSet(true);
    return this;
  }

  public void unsetForceNotCache() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __FORCENOTCACHE_ISSET_ID);
  }

  /** Returns true if field forceNotCache is set (has been assigned a value) and false otherwise */
  public boolean isSetForceNotCache() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __FORCENOTCACHE_ISSET_ID);
  }

  public void setForceNotCacheIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __FORCENOTCACHE_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getExtraJson() {
    return this.extraJson;
  }

  public SendReviewRiskRequest setExtraJson(@org.apache.thrift.annotation.Nullable java.lang.String extraJson) {
    this.extraJson = extraJson;
    return this;
  }

  public void unsetExtraJson() {
    this.extraJson = null;
  }

  /** Returns true if field extraJson is set (has been assigned a value) and false otherwise */
  public boolean isSetExtraJson() {
    return this.extraJson != null;
  }

  public void setExtraJsonIsSet(boolean value) {
    if (!value) {
      this.extraJson = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case OBJECT_ID:
      if (value == null) {
        unsetObjectId();
      } else {
        setObjectId((java.lang.Long)value);
      }
      break;

    case OBJECT_TYPE:
      if (value == null) {
        unsetObjectType();
      } else {
        setObjectType((hust.software.elon.safety.common.domain.ObjectType)value);
      }
      break;

    case CONFIG_KEY:
      if (value == null) {
        unsetConfigKey();
      } else {
        setConfigKey((java.lang.String)value);
      }
      break;

    case TOKEN:
      if (value == null) {
        unsetToken();
      } else {
        setToken((java.lang.String)value);
      }
      break;

    case FORCE_NOT_CACHE:
      if (value == null) {
        unsetForceNotCache();
      } else {
        setForceNotCache((java.lang.Boolean)value);
      }
      break;

    case EXTRA_JSON:
      if (value == null) {
        unsetExtraJson();
      } else {
        setExtraJson((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case OBJECT_ID:
      return getObjectId();

    case OBJECT_TYPE:
      return getObjectType();

    case CONFIG_KEY:
      return getConfigKey();

    case TOKEN:
      return getToken();

    case FORCE_NOT_CACHE:
      return isForceNotCache();

    case EXTRA_JSON:
      return getExtraJson();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case OBJECT_ID:
      return isSetObjectId();
    case OBJECT_TYPE:
      return isSetObjectType();
    case CONFIG_KEY:
      return isSetConfigKey();
    case TOKEN:
      return isSetToken();
    case FORCE_NOT_CACHE:
      return isSetForceNotCache();
    case EXTRA_JSON:
      return isSetExtraJson();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof SendReviewRiskRequest)
      return this.equals((SendReviewRiskRequest)that);
    return false;
  }

  public boolean equals(SendReviewRiskRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_objectId = true;
    boolean that_present_objectId = true;
    if (this_present_objectId || that_present_objectId) {
      if (!(this_present_objectId && that_present_objectId))
        return false;
      if (this.objectId != that.objectId)
        return false;
    }

    boolean this_present_objectType = true && this.isSetObjectType();
    boolean that_present_objectType = true && that.isSetObjectType();
    if (this_present_objectType || that_present_objectType) {
      if (!(this_present_objectType && that_present_objectType))
        return false;
      if (!this.objectType.equals(that.objectType))
        return false;
    }

    boolean this_present_configKey = true && this.isSetConfigKey();
    boolean that_present_configKey = true && that.isSetConfigKey();
    if (this_present_configKey || that_present_configKey) {
      if (!(this_present_configKey && that_present_configKey))
        return false;
      if (!this.configKey.equals(that.configKey))
        return false;
    }

    boolean this_present_token = true && this.isSetToken();
    boolean that_present_token = true && that.isSetToken();
    if (this_present_token || that_present_token) {
      if (!(this_present_token && that_present_token))
        return false;
      if (!this.token.equals(that.token))
        return false;
    }

    boolean this_present_forceNotCache = true;
    boolean that_present_forceNotCache = true;
    if (this_present_forceNotCache || that_present_forceNotCache) {
      if (!(this_present_forceNotCache && that_present_forceNotCache))
        return false;
      if (this.forceNotCache != that.forceNotCache)
        return false;
    }

    boolean this_present_extraJson = true && this.isSetExtraJson();
    boolean that_present_extraJson = true && that.isSetExtraJson();
    if (this_present_extraJson || that_present_extraJson) {
      if (!(this_present_extraJson && that_present_extraJson))
        return false;
      if (!this.extraJson.equals(that.extraJson))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(objectId);

    hashCode = hashCode * 8191 + ((isSetObjectType()) ? 131071 : 524287);
    if (isSetObjectType())
      hashCode = hashCode * 8191 + objectType.getValue();

    hashCode = hashCode * 8191 + ((isSetConfigKey()) ? 131071 : 524287);
    if (isSetConfigKey())
      hashCode = hashCode * 8191 + configKey.hashCode();

    hashCode = hashCode * 8191 + ((isSetToken()) ? 131071 : 524287);
    if (isSetToken())
      hashCode = hashCode * 8191 + token.hashCode();

    hashCode = hashCode * 8191 + ((forceNotCache) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((isSetExtraJson()) ? 131071 : 524287);
    if (isSetExtraJson())
      hashCode = hashCode * 8191 + extraJson.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(SendReviewRiskRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetObjectId(), other.isSetObjectId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetObjectId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.objectId, other.objectId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetObjectType(), other.isSetObjectType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetObjectType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.objectType, other.objectType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetConfigKey(), other.isSetConfigKey());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetConfigKey()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.configKey, other.configKey);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetToken(), other.isSetToken());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetToken()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.token, other.token);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetForceNotCache(), other.isSetForceNotCache());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetForceNotCache()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.forceNotCache, other.forceNotCache);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetExtraJson(), other.isSetExtraJson());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExtraJson()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.extraJson, other.extraJson);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("SendReviewRiskRequest(");
    boolean first = true;

    sb.append("objectId:");
    sb.append(this.objectId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("objectType:");
    if (this.objectType == null) {
      sb.append("null");
    } else {
      sb.append(this.objectType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("configKey:");
    if (this.configKey == null) {
      sb.append("null");
    } else {
      sb.append(this.configKey);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("token:");
    if (this.token == null) {
      sb.append("null");
    } else {
      sb.append(this.token);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("forceNotCache:");
    sb.append(this.forceNotCache);
    first = false;
    if (!first) sb.append(", ");
    sb.append("extraJson:");
    if (this.extraJson == null) {
      sb.append("null");
    } else {
      sb.append(this.extraJson);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'objectId' because it's a primitive and you chose the non-beans generator.
    if (configKey == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'configKey' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SendReviewRiskRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SendReviewRiskRequestStandardScheme getScheme() {
      return new SendReviewRiskRequestStandardScheme();
    }
  }

  private static class SendReviewRiskRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<SendReviewRiskRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SendReviewRiskRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // OBJECT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.objectId = iprot.readI64();
              struct.setObjectIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // OBJECT_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.objectType = hust.software.elon.safety.common.domain.ObjectType.findByValue(iprot.readI32());
              struct.setObjectTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CONFIG_KEY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.configKey = iprot.readString();
              struct.setConfigKeyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TOKEN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.token = iprot.readString();
              struct.setTokenIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // FORCE_NOT_CACHE
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.forceNotCache = iprot.readBool();
              struct.setForceNotCacheIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // EXTRA_JSON
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.extraJson = iprot.readString();
              struct.setExtraJsonIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetObjectId()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'objectId' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, SendReviewRiskRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(OBJECT_ID_FIELD_DESC);
      oprot.writeI64(struct.objectId);
      oprot.writeFieldEnd();
      if (struct.objectType != null) {
        oprot.writeFieldBegin(OBJECT_TYPE_FIELD_DESC);
        oprot.writeI32(struct.objectType.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.configKey != null) {
        oprot.writeFieldBegin(CONFIG_KEY_FIELD_DESC);
        oprot.writeString(struct.configKey);
        oprot.writeFieldEnd();
      }
      if (struct.token != null) {
        oprot.writeFieldBegin(TOKEN_FIELD_DESC);
        oprot.writeString(struct.token);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(FORCE_NOT_CACHE_FIELD_DESC);
      oprot.writeBool(struct.forceNotCache);
      oprot.writeFieldEnd();
      if (struct.extraJson != null) {
        oprot.writeFieldBegin(EXTRA_JSON_FIELD_DESC);
        oprot.writeString(struct.extraJson);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SendReviewRiskRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SendReviewRiskRequestTupleScheme getScheme() {
      return new SendReviewRiskRequestTupleScheme();
    }
  }

  private static class SendReviewRiskRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<SendReviewRiskRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SendReviewRiskRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI64(struct.objectId);
      oprot.writeString(struct.configKey);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetObjectType()) {
        optionals.set(0);
      }
      if (struct.isSetToken()) {
        optionals.set(1);
      }
      if (struct.isSetForceNotCache()) {
        optionals.set(2);
      }
      if (struct.isSetExtraJson()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetObjectType()) {
        oprot.writeI32(struct.objectType.getValue());
      }
      if (struct.isSetToken()) {
        oprot.writeString(struct.token);
      }
      if (struct.isSetForceNotCache()) {
        oprot.writeBool(struct.forceNotCache);
      }
      if (struct.isSetExtraJson()) {
        oprot.writeString(struct.extraJson);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SendReviewRiskRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.objectId = iprot.readI64();
      struct.setObjectIdIsSet(true);
      struct.configKey = iprot.readString();
      struct.setConfigKeyIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.objectType = hust.software.elon.safety.common.domain.ObjectType.findByValue(iprot.readI32());
        struct.setObjectTypeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.token = iprot.readString();
        struct.setTokenIsSet(true);
      }
      if (incoming.get(2)) {
        struct.forceNotCache = iprot.readBool();
        struct.setForceNotCacheIsSet(true);
      }
      if (incoming.get(3)) {
        struct.extraJson = iprot.readString();
        struct.setExtraJsonIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

