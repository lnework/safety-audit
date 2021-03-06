/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package hust.software.elon.safety.risk.service;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-05-11")
public class RiskService {

  public interface Iface {

    public hust.software.elon.safety.risk.domain.SendReviewRiskResponse sendToReviewRisk(hust.software.elon.safety.risk.domain.SendReviewRiskRequest req) throws org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void sendToReviewRisk(hust.software.elon.safety.risk.domain.SendReviewRiskRequest req, org.apache.thrift.async.AsyncMethodCallback<hust.software.elon.safety.risk.domain.SendReviewRiskResponse> resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public hust.software.elon.safety.risk.domain.SendReviewRiskResponse sendToReviewRisk(hust.software.elon.safety.risk.domain.SendReviewRiskRequest req) throws org.apache.thrift.TException
    {
      send_sendToReviewRisk(req);
      return recv_sendToReviewRisk();
    }

    public void send_sendToReviewRisk(hust.software.elon.safety.risk.domain.SendReviewRiskRequest req) throws org.apache.thrift.TException
    {
      sendToReviewRisk_args args = new sendToReviewRisk_args();
      args.setReq(req);
      sendBase("sendToReviewRisk", args);
    }

    public hust.software.elon.safety.risk.domain.SendReviewRiskResponse recv_sendToReviewRisk() throws org.apache.thrift.TException
    {
      sendToReviewRisk_result result = new sendToReviewRisk_result();
      receiveBase(result, "sendToReviewRisk");
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "sendToReviewRisk failed: unknown result");
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void sendToReviewRisk(hust.software.elon.safety.risk.domain.SendReviewRiskRequest req, org.apache.thrift.async.AsyncMethodCallback<hust.software.elon.safety.risk.domain.SendReviewRiskResponse> resultHandler) throws org.apache.thrift.TException {
      checkReady();
      sendToReviewRisk_call method_call = new sendToReviewRisk_call(req, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class sendToReviewRisk_call extends org.apache.thrift.async.TAsyncMethodCall<hust.software.elon.safety.risk.domain.SendReviewRiskResponse> {
      private hust.software.elon.safety.risk.domain.SendReviewRiskRequest req;
      public sendToReviewRisk_call(hust.software.elon.safety.risk.domain.SendReviewRiskRequest req, org.apache.thrift.async.AsyncMethodCallback<hust.software.elon.safety.risk.domain.SendReviewRiskResponse> resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.req = req;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("sendToReviewRisk", org.apache.thrift.protocol.TMessageType.CALL, 0));
        sendToReviewRisk_args args = new sendToReviewRisk_args();
        args.setReq(req);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public hust.software.elon.safety.risk.domain.SendReviewRiskResponse getResult() throws org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new java.lang.IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_sendToReviewRisk();
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final org.slf4j.Logger _LOGGER = org.slf4j.LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new java.util.HashMap<java.lang.String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, java.util.Map<java.lang.String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> java.util.Map<java.lang.String,  org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> getProcessMap(java.util.Map<java.lang.String, org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("sendToReviewRisk", new sendToReviewRisk());
      return processMap;
    }

    public static class sendToReviewRisk<I extends Iface> extends org.apache.thrift.ProcessFunction<I, sendToReviewRisk_args> {
      public sendToReviewRisk() {
        super("sendToReviewRisk");
      }

      public sendToReviewRisk_args getEmptyArgsInstance() {
        return new sendToReviewRisk_args();
      }

      protected boolean isOneway() {
        return false;
      }

      @Override
      protected boolean rethrowUnhandledExceptions() {
        return false;
      }

      public sendToReviewRisk_result getResult(I iface, sendToReviewRisk_args args) throws org.apache.thrift.TException {
        sendToReviewRisk_result result = new sendToReviewRisk_result();
        result.success = iface.sendToReviewRisk(args.req);
        return result;
      }
    }

  }

  public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
    private static final org.slf4j.Logger _LOGGER = org.slf4j.LoggerFactory.getLogger(AsyncProcessor.class.getName());
    public AsyncProcessor(I iface) {
      super(iface, getProcessMap(new java.util.HashMap<java.lang.String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
    }

    protected AsyncProcessor(I iface, java.util.Map<java.lang.String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends AsyncIface> java.util.Map<java.lang.String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(java.util.Map<java.lang.String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      processMap.put("sendToReviewRisk", new sendToReviewRisk());
      return processMap;
    }

    public static class sendToReviewRisk<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, sendToReviewRisk_args, hust.software.elon.safety.risk.domain.SendReviewRiskResponse> {
      public sendToReviewRisk() {
        super("sendToReviewRisk");
      }

      public sendToReviewRisk_args getEmptyArgsInstance() {
        return new sendToReviewRisk_args();
      }

      public org.apache.thrift.async.AsyncMethodCallback<hust.software.elon.safety.risk.domain.SendReviewRiskResponse> getResultHandler(final org.apache.thrift.server.AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new org.apache.thrift.async.AsyncMethodCallback<hust.software.elon.safety.risk.domain.SendReviewRiskResponse>() { 
          public void onComplete(hust.software.elon.safety.risk.domain.SendReviewRiskResponse o) {
            sendToReviewRisk_result result = new sendToReviewRisk_result();
            result.success = o;
            try {
              fcall.sendResponse(fb, result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
            } catch (org.apache.thrift.transport.TTransportException e) {
              _LOGGER.error("TTransportException writing to internal frame buffer", e);
              fb.close();
            } catch (java.lang.Exception e) {
              _LOGGER.error("Exception writing to internal frame buffer", e);
              onError(e);
            }
          }
          public void onError(java.lang.Exception e) {
            byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
            org.apache.thrift.TSerializable msg;
            sendToReviewRisk_result result = new sendToReviewRisk_result();
            if (e instanceof org.apache.thrift.transport.TTransportException) {
              _LOGGER.error("TTransportException inside handler", e);
              fb.close();
              return;
            } else if (e instanceof org.apache.thrift.TApplicationException) {
              _LOGGER.error("TApplicationException inside handler", e);
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.thrift.TApplicationException)e;
            } else {
              _LOGGER.error("Exception inside handler", e);
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
            } catch (java.lang.Exception ex) {
              _LOGGER.error("Exception writing to internal frame buffer", ex);
              fb.close();
            }
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, sendToReviewRisk_args args, org.apache.thrift.async.AsyncMethodCallback<hust.software.elon.safety.risk.domain.SendReviewRiskResponse> resultHandler) throws org.apache.thrift.TException {
        iface.sendToReviewRisk(args.req,resultHandler);
      }
    }

  }

  public static class sendToReviewRisk_args implements org.apache.thrift.TBase<sendToReviewRisk_args, sendToReviewRisk_args._Fields>, java.io.Serializable, Cloneable, Comparable<sendToReviewRisk_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("sendToReviewRisk_args");

    private static final org.apache.thrift.protocol.TField REQ_FIELD_DESC = new org.apache.thrift.protocol.TField("req", org.apache.thrift.protocol.TType.STRUCT, (short)1);

    private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new sendToReviewRisk_argsStandardSchemeFactory();
    private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new sendToReviewRisk_argsTupleSchemeFactory();

    public @org.apache.thrift.annotation.Nullable hust.software.elon.safety.risk.domain.SendReviewRiskRequest req; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      REQ((short)1, "req");

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
          case 1: // REQ
            return REQ;
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
    public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.REQ, new org.apache.thrift.meta_data.FieldMetaData("req", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, hust.software.elon.safety.risk.domain.SendReviewRiskRequest.class)));
      metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(sendToReviewRisk_args.class, metaDataMap);
    }

    public sendToReviewRisk_args() {
    }

    public sendToReviewRisk_args(
      hust.software.elon.safety.risk.domain.SendReviewRiskRequest req)
    {
      this();
      this.req = req;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public sendToReviewRisk_args(sendToReviewRisk_args other) {
      if (other.isSetReq()) {
        this.req = new hust.software.elon.safety.risk.domain.SendReviewRiskRequest(other.req);
      }
    }

    public sendToReviewRisk_args deepCopy() {
      return new sendToReviewRisk_args(this);
    }

    @Override
    public void clear() {
      this.req = null;
    }

    @org.apache.thrift.annotation.Nullable
    public hust.software.elon.safety.risk.domain.SendReviewRiskRequest getReq() {
      return this.req;
    }

    public sendToReviewRisk_args setReq(@org.apache.thrift.annotation.Nullable hust.software.elon.safety.risk.domain.SendReviewRiskRequest req) {
      this.req = req;
      return this;
    }

    public void unsetReq() {
      this.req = null;
    }

    /** Returns true if field req is set (has been assigned a value) and false otherwise */
    public boolean isSetReq() {
      return this.req != null;
    }

    public void setReqIsSet(boolean value) {
      if (!value) {
        this.req = null;
      }
    }

    public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
      switch (field) {
      case REQ:
        if (value == null) {
          unsetReq();
        } else {
          setReq((hust.software.elon.safety.risk.domain.SendReviewRiskRequest)value);
        }
        break;

      }
    }

    @org.apache.thrift.annotation.Nullable
    public java.lang.Object getFieldValue(_Fields field) {
      switch (field) {
      case REQ:
        return getReq();

      }
      throw new java.lang.IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new java.lang.IllegalArgumentException();
      }

      switch (field) {
      case REQ:
        return isSetReq();
      }
      throw new java.lang.IllegalStateException();
    }

    @Override
    public boolean equals(java.lang.Object that) {
      if (that instanceof sendToReviewRisk_args)
        return this.equals((sendToReviewRisk_args)that);
      return false;
    }

    public boolean equals(sendToReviewRisk_args that) {
      if (that == null)
        return false;
      if (this == that)
        return true;

      boolean this_present_req = true && this.isSetReq();
      boolean that_present_req = true && that.isSetReq();
      if (this_present_req || that_present_req) {
        if (!(this_present_req && that_present_req))
          return false;
        if (!this.req.equals(that.req))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int hashCode = 1;

      hashCode = hashCode * 8191 + ((isSetReq()) ? 131071 : 524287);
      if (isSetReq())
        hashCode = hashCode * 8191 + req.hashCode();

      return hashCode;
    }

    @Override
    public int compareTo(sendToReviewRisk_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = java.lang.Boolean.compare(isSetReq(), other.isSetReq());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetReq()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.req, other.req);
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
      java.lang.StringBuilder sb = new java.lang.StringBuilder("sendToReviewRisk_args(");
      boolean first = true;

      sb.append("req:");
      if (this.req == null) {
        sb.append("null");
      } else {
        sb.append(this.req);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
      if (req != null) {
        req.validate();
      }
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
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class sendToReviewRisk_argsStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public sendToReviewRisk_argsStandardScheme getScheme() {
        return new sendToReviewRisk_argsStandardScheme();
      }
    }

    private static class sendToReviewRisk_argsStandardScheme extends org.apache.thrift.scheme.StandardScheme<sendToReviewRisk_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, sendToReviewRisk_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // REQ
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.req = new hust.software.elon.safety.risk.domain.SendReviewRiskRequest();
                struct.req.read(iprot);
                struct.setReqIsSet(true);
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
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, sendToReviewRisk_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.req != null) {
          oprot.writeFieldBegin(REQ_FIELD_DESC);
          struct.req.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class sendToReviewRisk_argsTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public sendToReviewRisk_argsTupleScheme getScheme() {
        return new sendToReviewRisk_argsTupleScheme();
      }
    }

    private static class sendToReviewRisk_argsTupleScheme extends org.apache.thrift.scheme.TupleScheme<sendToReviewRisk_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, sendToReviewRisk_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet optionals = new java.util.BitSet();
        if (struct.isSetReq()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetReq()) {
          struct.req.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, sendToReviewRisk_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.req = new hust.software.elon.safety.risk.domain.SendReviewRiskRequest();
          struct.req.read(iprot);
          struct.setReqIsSet(true);
        }
      }
    }

    private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
      return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
    }
  }

  public static class sendToReviewRisk_result implements org.apache.thrift.TBase<sendToReviewRisk_result, sendToReviewRisk_result._Fields>, java.io.Serializable, Cloneable, Comparable<sendToReviewRisk_result>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("sendToReviewRisk_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.STRUCT, (short)0);

    private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new sendToReviewRisk_resultStandardSchemeFactory();
    private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new sendToReviewRisk_resultTupleSchemeFactory();

    public @org.apache.thrift.annotation.Nullable hust.software.elon.safety.risk.domain.SendReviewRiskResponse success; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success");

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
          case 0: // SUCCESS
            return SUCCESS;
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
    public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, hust.software.elon.safety.risk.domain.SendReviewRiskResponse.class)));
      metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(sendToReviewRisk_result.class, metaDataMap);
    }

    public sendToReviewRisk_result() {
    }

    public sendToReviewRisk_result(
      hust.software.elon.safety.risk.domain.SendReviewRiskResponse success)
    {
      this();
      this.success = success;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public sendToReviewRisk_result(sendToReviewRisk_result other) {
      if (other.isSetSuccess()) {
        this.success = new hust.software.elon.safety.risk.domain.SendReviewRiskResponse(other.success);
      }
    }

    public sendToReviewRisk_result deepCopy() {
      return new sendToReviewRisk_result(this);
    }

    @Override
    public void clear() {
      this.success = null;
    }

    @org.apache.thrift.annotation.Nullable
    public hust.software.elon.safety.risk.domain.SendReviewRiskResponse getSuccess() {
      return this.success;
    }

    public sendToReviewRisk_result setSuccess(@org.apache.thrift.annotation.Nullable hust.software.elon.safety.risk.domain.SendReviewRiskResponse success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((hust.software.elon.safety.risk.domain.SendReviewRiskResponse)value);
        }
        break;

      }
    }

    @org.apache.thrift.annotation.Nullable
    public java.lang.Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      }
      throw new java.lang.IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new java.lang.IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      }
      throw new java.lang.IllegalStateException();
    }

    @Override
    public boolean equals(java.lang.Object that) {
      if (that instanceof sendToReviewRisk_result)
        return this.equals((sendToReviewRisk_result)that);
      return false;
    }

    public boolean equals(sendToReviewRisk_result that) {
      if (that == null)
        return false;
      if (this == that)
        return true;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int hashCode = 1;

      hashCode = hashCode * 8191 + ((isSetSuccess()) ? 131071 : 524287);
      if (isSetSuccess())
        hashCode = hashCode * 8191 + success.hashCode();

      return hashCode;
    }

    @Override
    public int compareTo(sendToReviewRisk_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = java.lang.Boolean.compare(isSetSuccess(), other.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
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
      java.lang.StringBuilder sb = new java.lang.StringBuilder("sendToReviewRisk_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
      if (success != null) {
        success.validate();
      }
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
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class sendToReviewRisk_resultStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public sendToReviewRisk_resultStandardScheme getScheme() {
        return new sendToReviewRisk_resultStandardScheme();
      }
    }

    private static class sendToReviewRisk_resultStandardScheme extends org.apache.thrift.scheme.StandardScheme<sendToReviewRisk_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, sendToReviewRisk_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.success = new hust.software.elon.safety.risk.domain.SendReviewRiskResponse();
                struct.success.read(iprot);
                struct.setSuccessIsSet(true);
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
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, sendToReviewRisk_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          struct.success.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class sendToReviewRisk_resultTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public sendToReviewRisk_resultTupleScheme getScheme() {
        return new sendToReviewRisk_resultTupleScheme();
      }
    }

    private static class sendToReviewRisk_resultTupleScheme extends org.apache.thrift.scheme.TupleScheme<sendToReviewRisk_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, sendToReviewRisk_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet optionals = new java.util.BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetSuccess()) {
          struct.success.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, sendToReviewRisk_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.success = new hust.software.elon.safety.risk.domain.SendReviewRiskResponse();
          struct.success.read(iprot);
          struct.setSuccessIsSet(true);
        }
      }
    }

    private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
      return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
    }
  }

}
